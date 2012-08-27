/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.support.FormatNumber;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 *
 * @author absvalenzuela
 */
@Service("preparedReportImportCeilingBudgetManagement")
public class PreparedReportImportCeilingBudgetManagementImpl extends ManagementBase
        implements PreparedReportImportCeilingBudgetManagement, Serializable {

    @Override
    public String createFooterColumnReport(List<BudgetKeyItemEntity> layout) {
        String sMessageFooter = "";
        String footerColumnReport = "";


        byte caracter = 65;
        for (BudgetKeyItemEntity entity : layout) {
            sMessageFooter = Character.toString((char) caracter);

            sMessageFooter = sMessageFooter + this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.separador")
                    + entity.getBudgetKeyDescription();
            footerColumnReport = footerColumnReport + (sMessageFooter) + getMessage("ppp.techo.printerrorceilingbudget.separador");
            caracter++;
        }
        sMessageFooter = Character.toString((char) caracter);
        sMessageFooter = sMessageFooter + " = " + getMessage("ppp.techo.printerrorceilingbudget.importe");
        footerColumnReport = footerColumnReport + (sMessageFooter);

        return footerColumnReport;
    }

    @Override
    public List<BudgetKeyItemEntity> createLayoutReportImport(List<BudgetKeyItemEntity> layout) {
        String sMessageFooter = null;
        List<BudgetKeyItemEntity> entities = new ArrayList<BudgetKeyItemEntity>();
        BudgetKeyItemEntity obj = new BudgetKeyItemEntity();
        obj.setBudgetKeyName(this.getMessage("ppp.techo.printerrorceilingbudget.columnidentificador"));
        entities.add(obj);

        obj = new BudgetKeyItemEntity();
        obj.setBudgetKeyName(this.getMessage("ppp.techo.printerrorceilingbudget.columnnumero"));
        entities.add(obj);
        for (BudgetKeyItemEntity entity : layout) {
            entities.add(entity);
        }
        obj = new BudgetKeyItemEntity();
        obj.setBudgetKeyName(this.getMessage("ppp.techo.printerrorceilingbudget.columnimporte"));
        entities.add(obj);

        byte caracter = 65;
        for (BudgetKeyItemEntity entity : layout) {
            sMessageFooter = Character.toString((char) caracter);
            obj = new BudgetKeyItemEntity();
            obj.setBudgetKeyName(sMessageFooter);

            entities.add(obj);
            caracter++;
        }
        obj = new BudgetKeyItemEntity();
        sMessageFooter = Character.toString((char) caracter);
        obj.setBudgetKeyName(sMessageFooter);
        entities.add(obj);

        return entities;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> createReportWithError(List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject,
            List<CellCeilingBudgetDto> lstObjectError) {
        List<List<CellCeilingBudgetDto>> lstElement = new ArrayList<List<CellCeilingBudgetDto>>();
        List<CellCeilingBudgetDto> lstCell = new ArrayList<CellCeilingBudgetDto>();

        CellCeilingBudgetDto objEntityReport = null;
        long countRow = 1;
        for (List<CellCeilingBudgetDto> cellEntity : listObject) {
            lstCell = new ArrayList<CellCeilingBudgetDto>();
            objEntityReport = new CellCeilingBudgetDto();
            lstCell.add(objEntityReport);

            objEntityReport = new CellCeilingBudgetDto();
            objEntityReport.setDataElement(String.valueOf(countRow));
            lstCell.add(objEntityReport);
            int iIndexMonto = 0;
            for (CellCeilingBudgetDto entity : cellEntity) {
                objEntityReport = entity;
                if (iIndexMonto == layout.size()) {
                    objEntityReport.setDataElement(FormatNumber.formatNumber(objEntityReport.getDataElement()));
                }
                lstCell.add(entity);
                iIndexMonto++;
            }

            for (CellCeilingBudgetDto entity : cellEntity) {
                objEntityReport = new CellCeilingBudgetDto();
                String message = this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiqueta.existente");
                for (CellCeilingBudgetDto cellEntityWithError : lstObjectError) {
                    if (cellEntityWithError.getRegisterId() == entity.getRegisterId()
                            && cellEntityWithError.getDataElement().equals(entity.getDataElement())) {

                        if (cellEntityWithError.getMesageError() != null) {
                            message = this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiqueta.noexistente");

                            CellCeilingBudgetDto objAux = lstCell.get(0);
                            objAux.setDataElement(this.getMessage("ppp.techo.printerrorceilingbudget.rowerror"));
                            break;
                        }
                    }
                }
                objEntityReport.setDataElement(message);
                lstCell.add(objEntityReport);
            }
            countRow++;
            lstElement.add(lstCell);
        }

        return lstElement;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> createReportError(List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject, List<CellCeilingBudgetDto> listObjectError) {
        boolean rowWithError = false;
        List<List<CellCeilingBudgetDto>> lstElement = new ArrayList<List<CellCeilingBudgetDto>>();
        List<CellCeilingBudgetDto> lstCell = new ArrayList<CellCeilingBudgetDto>();

        CellCeilingBudgetDto objEntityReport = null;
        long countRow = 1;
        for (List<CellCeilingBudgetDto> cellEntity : listObject) {
            int iIndexMonto = 0;
            lstCell = new ArrayList<CellCeilingBudgetDto>();
            objEntityReport = new CellCeilingBudgetDto();
            objEntityReport.setDataElement(this.getMessage("ppp.techo.printerrorceilingbudget.rowerror"));
            lstCell.add(objEntityReport);

            objEntityReport = new CellCeilingBudgetDto();
            objEntityReport.setDataElement(String.valueOf(countRow));
            lstCell.add(objEntityReport);
            for (CellCeilingBudgetDto entity : cellEntity) {
                objEntityReport = null;
                for (CellCeilingBudgetDto cellEntityWithError : listObjectError) {
                    if (cellEntityWithError.getRegisterId() == entity.getRegisterId()) {
                        rowWithError = true;
                    } else {
                        if (iIndexMonto == layout.size()) {
                            rowWithError = true;
                        }
                    }
                    if (rowWithError) {
                        objEntityReport = entity;
                        if (iIndexMonto == layout.size()) {
                            objEntityReport.setDataElement(FormatNumber.formatNumber(objEntityReport.getDataElement()));
                        }
                        rowWithError = false;
                        break;
                    }
                }
                if (objEntityReport != null) {
                    lstCell.add(objEntityReport);
                    iIndexMonto++;
                }
            }

            for (CellCeilingBudgetDto entity : cellEntity) {
                objEntityReport = new CellCeilingBudgetDto();
                String message = this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiqueta.existente");;
                for (CellCeilingBudgetDto cellEntityWithError : listObjectError) {
                    if (cellEntityWithError.getRegisterId() == entity.getRegisterId()
                            && cellEntityWithError.getDataElement().equals(entity.getDataElement())) {
                        message = cellEntityWithError.getMesageError() != null
                                ? this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiqueta.noexistente")
                                : this.getMessage("ppp.techo.printerrorceilingbudget.etiqueta.etiqueta.existente");;
                        break;
                    }
                }
                objEntityReport.setDataElement(message);
                lstCell.add(objEntityReport);
            }
            countRow++;
            if (lstCell.size() > (layout.size() + 3)) {
                lstElement.add(lstCell);
            }
        }

        for (int iIndex = 0; iIndex < lstElement.size(); iIndex++) {
            for (int iIndexRow = 0; iIndexRow < lstElement.get(iIndex).size(); iIndexRow++) {
                CellCeilingBudgetDto objAux = lstElement.get(iIndex).get(1);
                objAux.setDataElement(String.valueOf(iIndex + 1));
                break;
            }
        }
        return lstElement;
    }

    @Override
    public boolean createDocumentExcel(String fileName, List<BudgetKeyItemEntity> layout, List<List<CellCeilingBudgetDto>> listObject) {
        boolean isfielCreate = false;
        int iRow = 0;
        int iCeil = 0;

        // Se crea el libro
        HSSFWorkbook libro = new HSSFWorkbook();

        // Se crea una hoja dentro del libro
        HSSFSheet hoja = libro.createSheet();

        HSSFRow fila = hoja.createRow(iRow);
        for (BudgetKeyItemEntity entity : layout) {
            HSSFCell celda = fila.createCell((short) iCeil);
            HSSFRichTextString texto = new HSSFRichTextString(entity.getBudgetKeyName());
            celda.setCellValue(texto);
            iCeil++;
        }

        iCeil = 0;
        iRow ++;
        for (List<CellCeilingBudgetDto> cellEntity : listObject) {
            iCeil = 0;
            fila = hoja.createRow(iRow);
            for (CellCeilingBudgetDto objDto : cellEntity) {
                HSSFCell celda = fila.createCell((short) iCeil);
                HSSFRichTextString texto = new HSSFRichTextString(objDto.getDataElement());
                celda.setCellValue(texto);
                iCeil++;
            }
            iRow ++;
        }

        try {
            FileOutputStream elFichero = new FileOutputStream(fileName);
            libro.write(elFichero);
            elFichero.close();
            isfielCreate = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isfielCreate;

    }
}
