module Main where

import Control.Monad (replicateM)
import Options.Applicative
import Options
import System.Random

import qualified InputUtils as IU
import qualified NaiveBayes as NB

import Data.Conduit
import System.IO
import Control.Monad.IO.Class
import Control.Monad.Trans.Resource
import qualified Data.Conduit.List as CL

import Control.Monad.Writer

source :: FilePath -> Source (ResourceT IO) String
source path = do
  bracketP
     (openFile path ReadMode)
     (\handle -> hClose handle)
     readByLine
  where
    readByLine handle = do
        eof <- liftIO $ hIsEOF handle
        if eof
            then return ()
            else do
                c <- liftIO $ hGetLine handle
                yield c
                readByLine handle

conduitParser :: IU.InputOpts -> Conduit String (ResourceT IO) (Maybe (NB.Object, NB.Label))
conduitParser opts = awaitForever $ yield . IU.toDataRow opts

program :: Options -> IO ()
program (Options infile inputOpts tries dataSplit) = do
    gs <- replicateM tries newStdGen
    maybeDataset <- runResourceT $ (source infile) $= (conduitParser inputOpts) $$ CL.consume
    let dataset = case (sequence maybeDataset) of
                    Nothing -> error "Bad CSV"
                    Just d -> d
    let (clr, msg) = runWriter $ NB.best gs dataSplit dataset
    putStrLn msg


main = execParser bayesOpts >>= program
