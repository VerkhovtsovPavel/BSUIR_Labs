package ui.menu;

import signals.Signal;
import spectrum.AmplitudeSpectrum;
import spectrum.PhaseSpectrum;
import ui.MainView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class ActionMenuCreator extends BaseMenuCreator {

    private JMenu actionMenu;

    public ActionMenuCreator(BaseMenuCreator next) {
        super(next);
    }

    @Override
    public JMenu createMenuTab() {
        actionMenu = new JMenu("Action");
        JMenuItem changeLogin = new JMenuItem("Spectrum");
        changeLogin.addActionListener(e -> {
            AmplitudeSpectrum amplitudeSpec = targetFrame.currentSignal().getAmplitudeSpec();
            amplitudeSpec.drawGraph("Amplitude Spectrum of Signal");
        });

        JMenuItem changePassword = new JMenuItem("Max filter");
        changePassword.addActionListener(e -> {
            List<Double> amplitudeSpec = targetFrame.currentSignal().getAmplitudeSpec().getValues();
            List<Double> phaseSpec = targetFrame.currentSignal().getPhaseSpec().getValues();
            int max = Integer.parseInt(JOptionPane.showInputDialog(targetFrame, "Please enter max value for filtration: "));
            AmplitudeSpectrum filteredAmplitudeSpectrum = new AmplitudeSpectrum(excludeComponents(amplitudeSpec, max, amplitudeSpec.size()), targetFrame.currentSignal().getN());
            PhaseSpectrum filteredPhaseSpectrum = new PhaseSpectrum(excludeComponents(phaseSpec, max, phaseSpec.size()), targetFrame.currentSignal().getN());
            new MainView(new Signal(filteredAmplitudeSpectrum, filteredPhaseSpectrum, targetFrame.currentSignal().getN(), targetFrame.currentSignal().getF())).showView();
        });

        JMenuItem maxFilter = new JMenuItem("Min filter");
        maxFilter.addActionListener(e -> {
            List<Double> amplitudeSpec = targetFrame.currentSignal().getAmplitudeSpec().getValues();
            List<Double> phaseSpec = targetFrame.currentSignal().getPhaseSpec().getValues();
            int min = Integer.parseInt(JOptionPane.showInputDialog(targetFrame, "Please enter min value for filtration: "));
            AmplitudeSpectrum filteredAmplitudeSpectrum = new AmplitudeSpectrum(excludeComponents(amplitudeSpec,0, min), targetFrame.currentSignal().getN());
            PhaseSpectrum filteredPhaseSpectrum = new PhaseSpectrum(excludeComponents(phaseSpec,0, min), targetFrame.currentSignal().getN());
            new MainView(new Signal(filteredAmplitudeSpectrum, filteredPhaseSpectrum, targetFrame.currentSignal().getN(), targetFrame.currentSignal().getF())).showView();
        });

        JMenuItem bandFilter = new JMenuItem("Band filter");
        bandFilter.addActionListener(e -> {
            List<Double> amplitudeSpec = targetFrame.currentSignal().getAmplitudeSpec().getValues();
            List<Double> phaseSpec = targetFrame.currentSignal().getPhaseSpec().getValues();
            int min = Integer.parseInt(JOptionPane.showInputDialog(targetFrame, "Please enter min value for filtration: "));
            int max = Integer.parseInt(JOptionPane.showInputDialog(targetFrame, "Please enter max value for filtration: "));
            AmplitudeSpectrum filteredAmplitudeSpectrum = new AmplitudeSpectrum(excludeComponents(excludeComponents(amplitudeSpec,0, min), max, amplitudeSpec.size()), targetFrame.currentSignal().getN());
            PhaseSpectrum filteredPhaseSpectrum = new PhaseSpectrum(excludeComponents(excludeComponents(phaseSpec,0, min), max, phaseSpec.size()), targetFrame.currentSignal().getN());
            new MainView(new Signal(filteredAmplitudeSpectrum, filteredPhaseSpectrum, targetFrame.currentSignal().getN(), targetFrame.currentSignal().getF())).showView();
        });
        actionMenu.add(changeLogin);
        actionMenu.add(changePassword);
        actionMenu.add(maxFilter);
        actionMenu.add(bandFilter);
        return actionMenu;
    }

    private <T> List<T> excludeComponents(List<T> initialList, int from, int to){
        List newList = new ArrayList(initialList);
        for(int i =from; i<to; i++){
            newList.set(i, 0.0d);
        }
        return newList;
    }
}
