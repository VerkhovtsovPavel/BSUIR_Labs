package by.bsuir.verkpavel.adb.atm_client.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

public class Check {
    public void generateCheck() {
        Docx docx = new Docx("res/checkTemp.docx");
        docx.setVariablePattern(new VariablePattern("#{", "}"));

        // preparing variables
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("#{firstname}", "Lukasz"));
        variables.addTextVariable(new TextVariable("#{lastname}", "Stypka"));

        // fill template
        docx.fillTemplate(variables);

        // save filled .docx file
        docx.save("reports/lstypka.docx");
    }

    // TODO Check on Linux
    public void openCheck(String filePath) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("reports/lstypka.docx"));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Check check = new Check();
        check.generateCheck();
        check.openCheck(null);
    }
}
