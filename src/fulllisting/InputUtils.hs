module InputUtils
( dos2unix
, dropBom
, split
, InputOpts(..)
, toDataRow
) where

-- | Set of options for 'parseCsv'
data InputOpts = InputOpts
    { delimiter :: String
    , stripHeader :: Bool
    , stripNumbering :: Bool
    }

-- | 'dos2unix' removes those filthy \r's
dos2unix :: String -> String
dos2unix = filter (/= '\r')

-- | 'dropBom' drops a UTF-8 BOM if it's present
dropBom :: String -> String
dropBom ('\xfeff':s) = s
dropBom s = s

-- TODO: support multichar delimiters
-- | 'split' is like 'words', but splits on given delimiter, not just space
split :: String -> String -> [String]
split ds@(d:_) s = case dropWhile (==d) s of
    "" -> []
    s' -> w : split ds s''
          where (w, s'') = break (==d) s'

-- | Parse row into (Object, Label) pairs
toDataRow :: InputOpts -> String -> Maybe ([Double], String)
toDataRow opts rawline = Just (o, l)
    where
        o = map (read :: String -> Double) . init $ values rawline
        l = last $ values $ dos2unix . dropBom $ rawline
        values = case stripNumbering opts of
                      True  -> tail . split d
                      False -> split d
                   where d = delimiter opts


