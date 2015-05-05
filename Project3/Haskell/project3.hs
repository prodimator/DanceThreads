module Main where
import Control.Concurrent
import Control.Concurrent.STM
import Control.Monad
import Data.Map (Map)           
import qualified Data.Map as Map
import System.IO
import System.Environment

import System.Random

takefork fork = takeTMVar fork
dropfork fork = putTMVar fork ()

randomDelay max = do
	n <- getStdRandom (randomR (1,max))
	threadDelay (n*100000)

data Leader = L [(String, Int)]

danceMapList = [("Waltz", -1), ("Tango", -1), ("Foxtrot", -1), ("Quickstep", -1), ("Rumba", -1), ("Samba", -1), ("Cha Cha", -1), ("Jive", -1)]
danceMap = Map.fromList danceMapList

danceKeys = ("Waltz", "Tango", "Foxtrot", "Quickstep", "Rumba", "Samba", "Cha Cha", "Jive")
emptyDance = "------"


{-|
tellLeader:: Leader -> String
tellLeader (Leader {id=i, }) = "Leader " ++ 


createFollowers
-}
main::IO ()
main = do
	argsList <- getArgs
	let n = read (argsList!!0)::Int
	let m = read (argsList!!1)::Int
	follower <- createFollowers m
	putStrLn ("looking at "++ show n ++ show m)
