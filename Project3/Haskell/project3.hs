import Control.Concurrent
import Control.Concurrent.STM
import Control.Monad

import System.Random

takefork fork = takeTMVar fork
dropfork fork = putTMVar fork ()

randomDelay max = do
	n <- getStdRandom (randomR (1,max))
	threadDelay (n*100000)

data Card = C ((String, Maybe ThreadId),(String, Maybe ThreadId),(String, Maybe ThreadId), (String, Maybe ThreadId), (String, Maybe ThreadId),(String, Maybe ThreadId),(String, Maybe ThreadId),(String, Maybe ThreadId))
data Leader = L ThreadId Card
data Follower = F ((Dance,Int))
data Dance = D String


dances = ("Waltz", "Tango", "Foxtrot", "Quickstep", "Rumba", "Samba", "Cha Cha", "Jive")
emptyDance = "------"

{-|
Create threads for each leader
Create threads for each follower

The process of asking should be:
	1. Check follower thread ID
	2. if dance card is full ::done::, elsewise do nextq
	3. If thread id isn't in dance list, send request to random non matched follower
	4. elsewise go to next follower

dance:: 
-}

getLeaderID:: Leader -> ThreadId
getLeaderID L id card = id

getDanceCard:: Leader -> Card
getDanceCard L id card = card

getFollowerID:: Follower -> ThreadId
getFollowerID F id idList = id

getLeadersFromFollower:: Follower -> [ThreadId]
getLeadersFromFollower F id idList = idList

hasDancedWithLeader:: ThreadId -> Follower -> Bool
hasDancedWithLeader id follower
	| id `elem` (getLeadersFromFollower follower) = True
	| otherwise = False

createLeader channelRequest followerChannel table m = do
	if length table <= m
		then
			generator <- newStdGen
			(followerId, newSeed) <- randomR(0,m) generator --get random follower
			danceId <- randomR(0, length dances) newSeed
			danceString <- fromMaybe $ lookup table danceId
			let dance = D danceString
			req = R (followerId, dance)
			writeChan channelRequest req
	

createFollowers:: Int -> TChan Follower -> IO ()
createFollowers m channelIn = do
	if m > -1
		then
			do
				let danceTable = (("Waltz", -1),("Tango",-1),("Foxtrot", -1),("Quickstep", -1),("Rumba",-1),("Samba",-1),("Cha Cha", -1), ("Jive",-1))
				follower <- F danceTable
				atomically $ writeTChan channelIn follower
				randomDelay 6
				createFollowers (m-1) channelIn
		else
			return ()


{-
askToDance::
-}
main:: IO ()
main = do
	argsList <- getArgs
	let n = read (head argsList)::Int
		m = read (tail argsList)::Int
	--Create a buffer for followers to sit in
	followerChannel <- atomically $ newTChan 
	--Create followers and dump into channel
	createFollowers m followerChannel
	leaders <- createLeaders n
	


