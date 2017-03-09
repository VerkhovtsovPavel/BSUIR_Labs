module Options
( Options(..)
, bayesOpts
) where

import InputUtils (InputOpts(..))
import Options.Applicative

data Options = Options
    { infile :: FilePath
    , inputOpts :: InputOpts
    , tries :: Int
    , dataSplit :: Double
    }


opts :: Parser Options
opts = Options
    <$> argument str
        ( metavar "INFILE" )
    <*> inputOpts
    <*> option auto
        ( long "tries"
       <> short 'n'
       <> value 3
       <> showDefault
       <> help "Numbers of classifiers to test" )
    <*> option auto
        ( long "data-split"
       <> short 's'
       <> value 0.8
       <> showDefault
       <> help "Training data share percent." )
  where
    inputOpts = InputOpts
        <$> option str
            ( long "delimiter"
           <> short 'd'
           <> value ","
           <> showDefault
           <> help "Input files delimiter" )
        <*> switch
            ( long "strip-header"
           <> help "Strip first line" )
        <*> switch
            ( long "strip-numbering"
           <> help "Strip first column (numbers)" )

bayesOpts :: ParserInfo Options
bayesOpts = info (helper <*> opts)
            ( fullDesc
           <> progDesc "Classify objects from INFILE using Naïve Bayes method"
           <> header "Naïve Bayes clustering sample program" )
