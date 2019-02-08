public void evaluateSingleInstance(int[] indexes, double[] values, SVMModel model) {
    SVMNode[] nodes = new SVMNode[values.length];
    for (int i = 0; i < values.length; i++)
    {
        SVMNode node = new SVMNode();
        node.index = indexes[i];
        node.value = values[i];
        nodes[i] = node;
    }
    int totalClasses = svm.getNrClass(model);
    int[] labels = new int[totalClasses];
    svm.getLabels(model, labels);
    double[] probEstimates = new double[totalClasses];
    double v = svm.predictProbability(model, nodes, probEstimates);
    for (int i = 0; i < totalClasses; i++) {
        System.out.print("(" + labels[i] + ":" + probEstimates[i] + ")");
    }
    System.out.println(" Prediction:" + v);
}