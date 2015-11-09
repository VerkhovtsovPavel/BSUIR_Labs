package by.bsuir.verkpavel.adb.atm_client.reports;

import java.util.HashMap;
import java.util.Map;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.DocxTemplate;
import pl.jsolve.templ4docx.core.VariablePattern;

public class Check {
    public static void generateCheck(){
        DocxTemplate template = new DocxTemplate();
        template.setVariablePattern(new VariablePattern("#{", "}")); 
        String path = "res/1.dotx";
         
        // prepare map of variables for template
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("#{firstName}", "John");
        variables.put("#{lastName}", "Sky");
         
        Docx filledTemplate = template.fillTemplate(path, variables);
         
        template.save(filledTemplate, "C://filledDocument.docx");
    }
    
    public static void main(String[] args){
        generateCheck();
    }
}
