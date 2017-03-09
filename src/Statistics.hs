
-- | Statistical mean of a list of values
mean :: Floating f => [f] -> f
mean xs = sum xs / genericLength xs

-- | Statistical dispersion of a list of values
dispersion :: Floating f => f -> [f] -> f
dispersion mean xs = s / (n - 1)
    where
        n = genericLength xs
        s = sum $ map ((** 2) . (mean -)) xs

-- | 2D version of `mean`
mean2d :: Floating f => [[f]] -> [f]
mean2d = map mean . transpose

-- | 2D version of `dispersion`
dispersion2d :: Floating f => [f] -> [[f]] -> [f]
dispersion2d mean2d xs = zipWith dispersion mean2d (transpose xs)

-- | Prabability density of the normal distribution
normalDensity :: (Floating f) => f -> f -> f -> f
normalDensity mean disp x = exp power / denom
    where
        power = - (x - mean) ^ 2 / (2 * disp)
        denom = sqrt (2 * pi * disp)

