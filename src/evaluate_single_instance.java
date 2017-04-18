public void evaluate_single_instance(int[] indexes, double[] values, svm_model model1) {
    svm_node[] nodes = new svm_node[values.length];
    for (int i = 0; i < values.length; i++)
    {
        svm_node node = new svm_node();
        node.index = indexes[i];
        node.value = values[i];
        nodes[i] = node;
    }
    int totalClasses = svm.svm_get_nr_class(model1);
    int[] labels = new int[totalClasses];
    svm.svm_get_labels(model1, labels);
    double[] prob_estimates = new double[totalClasses];
    double v = svm.svm_predict_probability(model1, nodes, prob_estimates);
    for (int i = 0; i < totalClasses; i++) {
        System.out.print("(" + labels[i] + ":" + prob_estimates[i] + ")");
    }
    System.out.println(" Prediction:" + v);
}