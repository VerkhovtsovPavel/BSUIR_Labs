package by.verkpavel.grafolnet;

import jnisvmlight.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Pavel_Verkhovtsov on 3/28/17.
 */
public class ClassificationSVM {

    public static int N = 2000; // number of training docs

    public static int M = 100; // max. number of features per doc

    public static void main(String[] args) throws Exception {
        System.setProperty("java.library.path", "/Users/Pavel_Verkhovtsov/Documents/Sandbox");
        System.getProperty("java.library.path");
        Random rd = new Random(new Date().getTime());

        // The trainer interface with the native communication to the SVM-light
        // shared libraries
        SVMLightInterface trainer = new SVMLightInterface();

        // The training data
        LabeledFeatureVector[] traindata = new LabeledFeatureVector[N];

        // Sort all feature vectors in ascedending order of feature dimensions
        // before training the model
        SVMLightInterface.SORT_INPUT_VECTORS = true;

        // Generate a randomly initialized array of N sparse training vectors
        // with at most M randomly chosen feature dimensions and values
        System.out.print("GENERATING A RANDOM TRAINING SET [N=" + N + ", M=" + M
                + "] ..");
        for (int i = 0; i < N; i++) {

            int nDims = 1 + rd.nextInt(M - 1);
            int[] dims = new int[nDims];
            double[] values = new double[nDims];

            // Generate a number of distinct random feature dimensions
            HashSet hashedDims = new HashSet();
            while (hashedDims.size() < nDims) {
                hashedDims.add(new Integer(1 + rd.nextInt(M - 1)));
            }

            // Fill the vectors
            int j = 0;
            for (Iterator iterator = hashedDims.iterator(); iterator.hasNext(); j++) {
                dims[j] = ((Integer) iterator.next()).intValue();
                values[j] = rd.nextDouble();
            }
            if (i % 10 == 0) {
                System.out.print(i + ".");
            }

            // Store dimension/value pairs in new LabeledFeatureVector object
            traindata[i] = new LabeledFeatureVector(nDims, dims,
                    values);

            // Use cosine similarities (LinearKernel with L2-normalized input vectors)
            traindata[i].normalizeL2();
        }
        System.out.println(" DONE.");

        // Initialize a new TrainingParamteres object with the default SVM-light
        // values
        TrainingParameters tp = new TrainingParameters();
        KernelParam kp = new KernelParam();
        kp.kernel_type = 2;
        // Switch on some debugging output
        tp.getLearningParameters().verbosity = 1;
        tp.setKernelParameters(kp);

        System.out.println("\nTRAINING SVM-light MODEL ..");
        SVMLightModel model = trainer.trainModel(traindata, tp);
        System.out.println(" DONE.");

        // Use this to store a model to a file or read a model from a URL.
        model.writeModelToFile("jni_model.dat");
        model = SVMLightModel.readSVMLightModelFromURL(new java.io.File("jni_model.dat").toURL());

        // Use the classifier on the randomly created feature vectors
        System.out.println("\nVALIDATING SVM-light MODEL in Java..");
        int precision = 0;
        for (int i = 0; i < N; i++) {

            // Classify a test vector using the Java object
            // (in a real application, this should not be one of the training vectors)
            double d = model.classify(traindata[i]);
            if ((traindata[i].getLabel() < 0 && d < 0)
                    || (traindata[i].getLabel() > 0 && d > 0)) {
                precision++;
            }
            if (i % 10 == 0) {
                System.out.print(i + ".");
            }
        }
        System.out.println(" DONE.");
        System.out.println("\n" + ((double) precision / N)
                + " PRECISION=RECALL ON RANDOM TRAINING SET.");

        System.out.println("\nVALIDATING SVM-light MODEL in Native Mode..");
        precision = 0;
        for (int i = 0; i < N; i++) {

            // Classify a test vector using the Native Interface
            // (in a real application, this should not be one of the training vectors)
            double d = trainer.classifyNative(traindata[i]);
            if ((traindata[i].getLabel() < 0 && d < 0)
                    || (traindata[i].getLabel() > 0 && d > 0)) {
                precision++;
            }
            if (i % 10 == 0) {
                System.out.print(i + ".");
            }
        }
        System.out.println(" DONE.");
        System.out.println("\n" + ((double) precision / N)
                + " PRECISION=RECALL ON RANDOM TRAINING SET.");

        // An alternative way to invoke the SVM-light interface similar to using the
        // command line parameters.
        //System.out
        //    .println("\nTRAINING SVM-light MODEL WITH COMMAND LINE PARAMETERS ..");
        //model = trainer.trainModel(traindata, new String[] { "-z", "c" });

        System.out.println(" DONE.");
    }
}
