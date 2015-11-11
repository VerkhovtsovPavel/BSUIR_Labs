package by.bsuir.verkpavel.adb.atm_client.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

//TODO Add check template
//TODO Add unique number in check number
public class Check {
    private String checkFilePath = "1.docx";
    
     public void generateCheck() {
        Docx docx = new Docx("res/reportsTemplates/checkTemp.docx");
        docx.setVariablePattern(new VariablePattern("#{", "}"));

        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("#{firstname}", "Lukasz"));
        variables.addTextVariable(new TextVariable("#{lastname}", "Stypka"));

        docx.fillTemplate(variables);

        docx.save("reports/"+checkFilePath);
    }

    public void openCheck(String filePath) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("reports/"+checkFilePath));
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
