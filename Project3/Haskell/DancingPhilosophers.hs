module Main where
import Control.Concurrent
import Control.Monad
import Data.Map (Map)
import Data.Maybe (Maybe)        
import qualified Data.Map as Map
import qualified Data.Maybe as Maybe
import System.IO
import System.Environment

import System.Random

randomDelay max = do
	n <- getStdRandom (randomR (1,max))
	threadDelay (n*100000)

data Leader = L Int (Map String Integer) deriving Show
data Follower = F Int (Map String Integer) deriving Show
data LeaderPostOffice = LPO [(MVar Int, MVar String)]
data FollowerPostOffice = FPO [(MVar Int, MVar Bool)]

danceMapList = [("Waltz", -1), ("Tango", -1), ("Foxtrot", -1), ("Quickstep", -1), ("Rumba", -1), ("Samba", -1), ("Cha Cha", -1), ("Jive", -1)]

danceKeys = ["Waltz", "Tango", "Foxtrot", "Quickstep", "Rumba", "Samba", "Cha Cha", "Jive"]
emptyDance = "------"

createLeader:: Int -> [(String, Integer)] -> Leader
createLeader id mapList = L id (Map.fromList mapList)

createFollower:: Int -> [(String, Integer)] -> Follower
createFollower id mapList = F id (Map.fromList mapList)

createLeaders:: Int -> [Leader] -> [Leader]
createLeaders n leaders
	| n > 0 = createLeaders (n-1) (leaders++[createLeader n danceMapList])
	| otherwise = leaders

createFollowers:: Int -> [Follower] -> [Follower]
createFollowers m followers
	| m > 0 = createFollowers (m-1) (followers++[createFollower m danceMapList])
	| otherwise = followers

getFollowerDanceCard:: Follower -> (Map String Integer)
getFollowerDanceCard (F id map) = map

updateFollowerDance:: Follower -> Integer -> String -> (Map String Integer)
updateFollowerDance f id dance = Map.insert dance id (getFollowerDanceCard f)

updateFollowerDanceCard:: Follower -> (Map String Integer) -> Follower
updateFollowerDanceCard f map = F (getFollowerID f) map

getFollowerID:: Follower -> Int
getFollowerID (F id map) = id

getLeaderDanceCard:: Leader -> (Map String Integer)
getLeaderDanceCard (L id map) = map

updateLeaderDance:: Leader -> Integer -> String -> (Map String Integer)
updateLeaderDance l id dance = Map.insert dance id (getLeaderDanceCard l)

updateLeaderDanceCard:: Leader -> (Map String Integer) -> Leader
updateLeaderDanceCard l map = L (getLeaderID l) map

getLeaderID:: Leader -> Int
getLeaderID (L id map) = id

canDance:: Follower -> Integer -> String -> Bool
canDance f id dance
	| Maybe.fromJust (Map.lookup dance (getFollowerDanceCard f)) == -1 = True
	| length (filter (==id) (Map.elems (getFollowerDanceCard f))) > 0 && length (filter (==id) (Map.elems (getFollowerDanceCard f))) < 2 = True
	| otherwise = False


getNextDance:: Leader -> [String] -> String
getNextDance l [] = ""
getNextDance l (x:xs)
	| Maybe.fromJust (Map.lookup x (getLeaderDanceCard l)) == -1 = x
	| otherwise = getNextDance l xs

lookupDanceCard:: String -> (Map String Integer) -> Integer
lookupDanceCard key map = Maybe.fromJust (Map.lookup key map)


getFollowerForDance key map = do
	if (lookupDanceCard key map) == -1
		then 
			do
				returnValue <- "------"
				return returnValue
	else
		do
			returnValue <- ("with " ++ (show (lookupDanceCard key map)))
			return returnValue


tellLeader:: Leader -> String
tellLeader (L id map) = "Leader " ++ show id ++ "\n" ++ "Waltz      " ++ getFollowerForDance "Waltz" map ++ "\n" ++ "Tango      " ++ getFollowerForDance "Tango" map ++ "\n" ++ "Foxtrot    " ++ getFollowerForDance "Foxtrot" map ++ "\n" ++ "Quickstep  " ++ getFollowerForDance "Quickstep" map ++ "\n" ++ "Rumba      " ++ getFollowerForDance "Rumba" map ++ "\n" ++ "Samba      " ++ getFollowerForDance "Samba" map ++ "\n" ++ "Cha Cha    " ++ getFollowerForDance "Cha Cha" map ++ "\n" ++ "Jive       " ++ getFollowerForDance "Jive" map

tellLeaders::[Leader] -> String
tellLeaders [] = ""
tellLeaders (x:xs) = tellLeader x ++ "\n\n" ++ tellLeaders xs

{-|
placeRequestInPostOffice request index postOffice
placeResponseInPostOffice response index postOffice

retrieveRequestFromPostOffice index postOffice
retrieveResponseFromPostOffice index postOffice

leader:: Int -> Int -> LeaderPostOffice -> FollowerPostOffice -> Leader -> Int -> IO ()
leader n m leaderPostOffice followerPostOffice leaderIn counter index = do
	if leaderDone leaderIn == True
		then
			do 
				return ()
	else
		do
			dance <- getNextDance leaderIn dances
			placeRequestInPostOffice dance index followerPostOffice
			leader n m leaderPostOffice followerPostOffice leaderIn (counter + 1) (index + 1 `mod` m)

follower:: Int -> Int -> LeaderPostOffice -> FollowerPostOffice -> Follower -> Int -> IO ()
follower n m leaderPostOffice followerPostOffice followerIn counter
	if followerDone followerIn == True
		then
			do
				return ()
	else
		do
			request <- retrieveRequestFromPostOffice (getID followerIn) followerPostOffice
			if canDance request
				then
					do
						placeResponseInPostOffice
						updateDanceCard 
-}


main::IO ()
main = do
	argsList <- getArgs
	let n = read (argsList!!0)::Int
	let m = read (argsList!!1)::Int
	let leaders = createLeaders n []
	let followers = createFollowers m []
	blankLeaders <- replicateM n $ newEmptyMVar
	blankFollowers <- replicateM m $ newEmptyMVar
	putStrLn $ tellLeaders (reverse leaders)