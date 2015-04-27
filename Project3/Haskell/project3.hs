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
data Follower = F ThreadId [ThreadId]

dances = ("Waltz", "Tango", "Foxtrot", "Quickstep", "Rumba", "Samba", "Cha Cha", "Jive")
emptyDance = "------"

{-|
Create threads for each leader
Create threads for each follower

The process of asking should be:
	1. Check follower thread ID
	2. if dance card is full ::done::, elsewise do next
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