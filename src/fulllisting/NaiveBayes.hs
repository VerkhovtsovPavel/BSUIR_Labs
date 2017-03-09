module NaiveBayes
( Class(..)
, Classifier
, Feature
, Object
, Label
, Dataset
, classMap
, train
, likelihood
, tellObj
, best
, splitDataset
) where

import System.Random
import Data.List
import Data.List.Extras
import Data.Map (toList, fromListWith)

import Control.Monad.Writer
import Control.Parallel.Strategies

import Statistics

type Feature = Double
type Object  = [Feature]
type Index   = Int
type Label   = String
type Dataset = [(Object, Label)]

data Class = Class
    { label :: String
    , aprioriP :: Double
    , cMean :: Object
    , cDispersion :: [Double]
    } deriving Show

data Classifier = Classifier
    { classes :: [Class]
    , trainedOn :: [Index]
    } deriving Show

-- | Groups objects into classes
classMap :: [(Object, Label)] -> [(Label, [Object])]
classMap h = toList $ fromListWith (++) [(label, [obj]) | (obj, label) <- h]

-- | Extracts available classes, centers and a priori probabilities from the training set
train :: Double -> Dataset -> Classifier
train total set =
    Classifier { classes = map makeClass . classMap $ set
               , trainedOn = [] }
    where
        makeClass (label, objs) =
            Class { label = label
                  , aprioriP = genericLength objs / total
                  , cMean = cmean
                  , cDispersion = cdisp }
          where
            cmean = mean2d objs
            cdisp = dispersion2d cmean objs

-- | Tests a classifier against test data returning number of errors
test :: Classifier -> Dataset -> Int
test model = length . filter (\(o, l) -> label (tellObj model o) == l)

-- | Probability of object being a part of class
likelihood :: (Floating f) => Object -> Class -> Double
likelihood x c = ap * pxc
    where
        ap  = aprioriP c
        cms = cMean c
        cds = cDispersion c
        pxc = product $ zipWith3 normalDensity cms cds x

-- | Tells the most appropriate class for an object
tellObj :: Classifier -> Object -> Class
tellObj model obj = argmax (likelihood obj) $ classes model

-- | Best classifier
best :: (RandomGen g) => [g] -> Double -> Dataset -> Writer String Classifier
best gs share set = do
    let model = train (genericLength set)
        cases = [ (model trn, tst) | (trn, tst) <- splits ]
        (best, tst) = argmin (\(m, tst) -> test m tst) cases
        errors = test best tst
        splits = parMap rpar (\g -> splitDataset g share set) gs
    tell (show best ++ "\nerrors: " ++ show errors)
    return best

-- | Splits a given dataset into train and test sets
splitDataset :: (RandomGen g) => g -> Double -> Dataset -> (Dataset, Dataset)
splitDataset g share set = branch share $ zip rs set
    where
        rs = randoms g :: [Double]
        branch share [] = ([], [])
        branch share ((v, x) : vxs)
            | v < share = (x : train, test)
            | otherwise = (train, x : test)
          where
            (train, test) = branch share vxs
