def predictPsychotype(feature: Features): Int {
    real_feature_column = real_valued_column(...)
    sparse_feature_column = sparse_column_with_hash_bucket(...)

	val estimator = SVM(
    example_id_column='id',
    feature_columns=[real_feature_column, sparse_feature_column],
    l2_regularization=10.0)

  	...

	val prediction = estimator.predict(feature)
	return formatPrediction(prediction)
}