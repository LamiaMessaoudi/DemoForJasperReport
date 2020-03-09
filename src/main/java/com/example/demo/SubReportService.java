package com.example.demo;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SubReportService {
    JasperDesign jasperDesign= new JasperDesign();
    JasperDesign jp= new JasperDesign();
    String parameterName="";
    File template = new File("C:\\Users\\Lamia\\JaspersoftWorkspace\\ExempleRapportsBeforePresentation\\Demotest.Jrxml");



    public void loadreport() throws JRException {

        jasperDesign = JRXmlLoader.load(template);
        jp=jasperDesign;
    }

    public void generateParameters(ArrayList<String> parametersList) throws JRException {
        //Object[] parameterList = jasperDesign.getParametersList().toArray();
        for (int i = 0; i < parametersList.size(); i++) {
            JRDesignParameter parameter = new JRDesignParameter();
            parameter.setName(parametersList.get(i));
            parameter.setValueClass(java.lang.String.class);
            jasperDesign.addParameter(parameter);

        }
    }

    public void generateFields(ArrayList<String> fieldsList) throws JRException {
        for (int i = 0; i < fieldsList.size(); i++) {

            JRDesignField field = new JRDesignField();
            field.setName(fieldsList.get(i));
            field.setValueClass(java.lang.String.class);
            jasperDesign.addField(field);

        }

    }

    public void generateQuery(String query){
        JRDesignQuery jdp=new JRDesignQuery();
        jdp.setText(query);
        jdp.setLanguage("SQL");
        jp.setQuery(jdp);

    }
    public void generateTitle(ArrayList<String> fieldsList)
    {
        JRDesignStaticText  staticText;
        JRDesignBand bndt;
        for (int i = 0; i < fieldsList.size(); i++) {

            staticText = new JRDesignStaticText();
            staticText.setX(i*100);
            staticText.setY(20);
            staticText.setWidth(100);
            staticText.setHeight(30);
            staticText.setForecolor(Color.white);
            staticText.setBackcolor(new Color(0x33, 0x33, 0x33));
            staticText.setText(fieldsList.get(i));
            bndt= (JRDesignBand) jp.getTitle();
            bndt.addElement(staticText);

        }
    }
    public void generateDetails(ArrayList<String> fieldsList)
    {
        JRDesignTextField textField;
        JRDesignExpression expression;
        JRDesignBand bnd;
        for (int i = 0; i < fieldsList.size(); i++) {

            textField = new JRDesignTextField();
            textField.setX(i*100);
            textField.setY(10);
            textField.setWidth(100);
            textField.setHeight(30);
            expression = new JRDesignExpression();
            String fieldname=fieldsList.get(i);
            expression.setText("$F{"+fieldname+"}");
            textField.setExpression(expression);
            bnd= (JRDesignBand) jp.getDetailSection().getBands()[0];
            bnd.addElement(textField);

        }
    }


    public void createSubReport() throws JRException {
        JRXmlWriter.writeReport(jp, "SUBREP_Lamia.jrxml", "UTF-8");
    }


    public void exportReport(String reportFormat) throws FileNotFoundException, JRException
    {
        String path="D:";
        File file = ResourceUtils.getFile("SUBREP_Lamia.jrxml");
        JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());

        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,null);


        if(reportFormat.equalsIgnoreCase("html"))
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\employee.html");
        }

        if(reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\employee.pdf");
        }
    }









    public void raporti(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/reis_db", "postgres", "postgresql");
                    JasperReport jreport = JasperCompileManager.compileReport("SUBREP_Lamia.jrxml");
                    JasperPrint jprint = JasperFillManager.fillReport(jreport, new HashMap(), conn);
                    JRExporter  exporter= new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"report.pdf");
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT,jprint);
                    exporter.exportReport();
                    //JasperViewer.viewReport(jprint, false);
                    conn.close();
                }catch (Exception ex){ex.printStackTrace();}
            }
        }).start();
    }
}
