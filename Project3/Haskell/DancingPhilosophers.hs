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

data Leader = L Int (Map String Integer)
data LeaderPostOffice = LPO [(MVar Int, MVar String)]
data FollowerPostOffice = FPO [(MVar Int, MVar Bool)]

danceMapList = [("Waltz", -1), ("Tango", -1), ("Foxtrot", -1), ("Quickstep", -1), ("Rumba", -1), ("Samba", -1), ("Cha Cha", -1), ("Jive", -1)]

danceKeys = ("Waltz", "Tango", "Foxtrot", "Quickstep", "Rumba", "Samba", "Cha Cha", "Jive")
emptyDance = "------"

createLeader:: Int -> [(String, Integer)] -> Leader
createLeader id mapList = L id (Map.fromList mapList)

createLeaders:: Int -> [Leader] -> [Leader]
createLeaders n leaders = do
	if n > 0
		then
			do
				leaders <- leaders ++ [(createLeader n danceMapList)]
				createLeaders (n-1) [leaders]
	else
		do 
			return leaders

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


main::IO ()
main = do
	argsList <- getArgs
	let n = read (argsList!!0)::Int
	let m = read (argsList!!1)::Int
	chan <- newChan
	let leader = createLeader 1 danceMapList
	putStrLn $ tellLeader leader