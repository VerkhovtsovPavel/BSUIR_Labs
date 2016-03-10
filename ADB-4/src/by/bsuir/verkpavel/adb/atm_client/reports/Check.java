package by.bsuir.verkpavel.adb.atm_client.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.DocxTemplate;
import pl.jsolve.templ4docx.core.VariablePattern;
import by.bsuir.verkpavel.adb.shared.OperationList;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class Check {
    private String checkFilePath;
    private OperationList operationList;
    private CheckTypes type;
    private String checkNumber;

    public Check(OperationList operationList, CheckTypes type) {
        this.checkNumber = generateCheckID();
        this.checkFilePath = checkNumber + ".docx";
        this.operationList = operationList;
        this.type = type;
    }

    public void generateCheck() {
        DocxTemplate template = new DocxTemplate();
        template.setVariablePattern(new VariablePattern("#{", "}")); 
         
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("#{checkNumber}", checkNumber);
        variables.put("#{balance}", String.format("%.2f", operationList.getOperation(OperationType.Balance)));
        variables.put("#{accountType}", ""+operationList.getOperation(OperationType.AccountType));
        variables.put("#{operator}", ""+operationList.getOperation(OperationType.Operator));
        variables.put("#{phoneNumber}", ""+operationList.getOperation(OperationType.PhoneNumber));
        variables.put("#{operationSum}", String.format("%.2f",operationList.getOperation(OperationType.OperationSum)));
        variables.put("#{date}", LocalDate.now().toString() +" "+LocalTime.now().toString());
              
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
