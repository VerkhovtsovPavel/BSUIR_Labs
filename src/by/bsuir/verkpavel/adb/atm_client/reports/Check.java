package by.bsuir.verkpavel.adb.atm_client.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.DocxTemplate;
import pl.jsolve.templ4docx.core.VariablePattern;
//TODO Change check templates
import by.bsuir.verkpavel.adb.shared.OperationList;

public class Check {
    private String checkFilePath;
    private OperationList operationList;
    private CheckTypes type;

    public Check(OperationList operationList, CheckTypes type) {
        this.checkFilePath = generateCheckID() + ".docx";
        this.operationList = operationList;
        this.type = type;
    }

    public void generateCheck() {
        DocxTemplate template = new DocxTemplate();
        template.setVariablePattern(new VariablePattern("#{", "}")); 
         
        // TODO fill all possible fields
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("#{firstName}", "John");
        variables.put("#{lastName}", "Sky");
         
        Docx filledTemplate = template.fillTemplate(getTemplatePathByType(type), variables);
         
        template.save(filledTemplate, "reports/" + checkFilePath);
    }

    public void openCheck() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("reports/" + checkFilePath));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Check check = new Check(null, CheckTypes.BalanceCheck);
        check.generateCheck();
        check.openCheck();
    }

    private String generateCheckID() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }

    private String getTemplatePathByType(CheckTypes type) {
        String templatePath = "res/reportsTemplates/";
        switch (type) {
        case BalanceCheck:
            templatePath += "balanceCheckTemp.docx";
            break;
        case CashWithdrawalCheck:
            templatePath += "cashWithdrawalCheckTemp.docx";
            break;
        case PaymentsCheck:
            templatePath += "paymentsCheckTemp.docx";
            break;
        }
        return templatePath;
    }
}
