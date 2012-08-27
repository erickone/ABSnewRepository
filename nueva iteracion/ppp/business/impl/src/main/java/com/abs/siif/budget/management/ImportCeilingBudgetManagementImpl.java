/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.management;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.budget.dao.CeilingConfigurationDao;
import com.abs.siif.budget.dao.ImportCeilingBudgetDao;
import com.abs.siif.budget.dto.CeilingConfigurationDto;
import com.abs.siif.budget.dto.CellCeilingBudgetDto;
import com.abs.siif.budget.dto.ImportFileCeillingBudgetDto;
import com.abs.siif.budget.dto.ValidateDeleteCeilingBudgetDto;
import com.abs.siif.budget.dto.ValidationNameEnum;
import com.abs.siif.budget.entities.BudgetKeyItemEntity;
import com.abs.siif.budget.entities.CeilingConfigurationEntity;
import com.abs.siif.budget.entities.CeillingBudgetEntity;
import com.abs.siif.budget.validator.CompCeilingBudget;
import com.abs.siif.budget.validator.CompositeValidation;
import com.abs.siif.budget.validator.ValidateCompImport;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;

/**
 *
 * @author absvalenzuela
 */
@Service("importCeilingBudgetManagement")
public class ImportCeilingBudgetManagementImpl extends ManagementBase
        implements ImportCeilingBudgetManagement, Serializable {

    @Resource(name = "ceilingConfigurationDao")
    private transient CeilingConfigurationDao itsCeilingConfigurationDao;

    @Resource(name = "importCeilingBudgetDao")
    private transient ImportCeilingBudgetDao itsImportCeilingBudgetDao;

    @Override
    public boolean validateFile(String pathFile, List<BudgetKeyItemEntity> objLayout) {
        boolean validateFile = true;
        try {
            /**
             * Creating Input Stream*
             */
            //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
            FileInputStream myInput = new FileInputStream(pathFile);

            /**
             * Create a POIFSFileSystem object*
             */
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            /**
             * Create a workbook using the File System*
             */
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            for (int iSheet = 0; iSheet < myWorkBook.getNumberOfSheets(); iSheet++) {
                /**
                 * Get the first sheet from workbook*
                 */
                HSSFSheet mySheet = myWorkBook.getSheetAt(iSheet);

                if (mySheet.getLastRowNum() > 0) {

                    Iterator rowIter = mySheet.rowIterator();
                    while (rowIter.hasNext()) {
                        HSSFRow myRow = (HSSFRow) rowIter.next();
                        if (myRow.getLastCellNum() - 1 != objLayout.size()) {
                            validateFile = false;
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return validateFile;
    }

    @Override
    public List<CeilingConfigurationDto> getAllCeillingconfigurationForYear(int year) {
        List<CeilingConfigurationDto> myCeilingList = new ArrayList<CeilingConfigurationDto>();
        CeilingConfigurationDto myDto = null;
        List<CeilingConfigurationEntity> myCeilingEntityList;
        myCeilingEntityList = itsCeilingConfigurationDao.getAllCeilingConfigurations();
        for (CeilingConfigurationEntity ceiling : myCeilingEntityList) {

            myDto = new CeilingConfigurationDto();

            if (ceiling.getCeilingConfigBudgetKey().getPresupuestalKeyDefinitionYear() == year) {
                myDto.setCeilingConfigId(ceiling.getCeilingConfigId());
                myDto.setCeilingConfigName(ceiling.getCeilingConfigName());
                myDto.setIsCeilingConfigActive(ceiling.isCeilingConfigActive());
                myDto.setCeilingConfigDesc(ceiling.getCeilingConfigDescription());
                myDto.setBudgetKeyItems(ceiling.getBudgetKeyItems());
                myDto.setBudgetKeyDefinition(ceiling.getCeilingConfigBudgetKey());
                myCeilingList.add(myDto);
            }

        }
        return myCeilingList;
    }

    @Override
    public List<List<CellCeilingBudgetDto>> readFiles(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files) {
        List<List<CellCeilingBudgetDto>> objList = new ArrayList<List<CellCeilingBudgetDto>>();

        List<CellCeilingBudgetDto> objRowCeiling = null;
        for (ImportFileCeillingBudgetDto importFile : files) {
            try {
                /**
                 * Creating Input Stream*
                 */
                //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
                FileInputStream myInput = new FileInputStream(importFile.getPathFile());

                /**
                 * Create a POIFSFileSystem object*
                 */
                POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                /**
                 * Create a workbook using the File System*
                 */
                HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

                for (int iSheet = 0; iSheet < myWorkBook.getNumberOfSheets(); iSheet++) {
                    /**
                     * Get the first sheet from workbook*
                     */
                    HSSFSheet mySheet = myWorkBook.getSheetAt(iSheet);

                    if (mySheet.getLastRowNum() > 0) {

                        Iterator rowIter = mySheet.rowIterator();
                        while (rowIter.hasNext()) {
                            HSSFRow myRow = (HSSFRow) rowIter.next();
                            if (myRow.getRowNum() > 0) {
                                objRowCeiling = new ArrayList<CellCeilingBudgetDto>();
                                Iterator cellIter = myRow.cellIterator();
                                while (cellIter.hasNext()) {
                                    HSSFCell myCell = (HSSFCell) cellIter.next();

                                    String valueCell = myCell.toString().replaceAll(",", "");
                                    Long typeElement = null;
                                    try {
                                        typeElement = objLayout.get(myCell.getCellNum()).getBudgetKeyId();
                                    } catch (Exception ex) {
                                    }

                                    int iCountElement = Integer.parseInt(this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar"));
                                    for (int iIndex = 0; iIndex < iCountElement; iIndex++) {
                                        Long element = Long.parseLong(this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar.element." + iIndex));
                                        if (typeElement != null && element.longValue() == typeElement.longValue()) {
                                            valueCell = String.valueOf(Double.valueOf(valueCell).longValue());
                                            int longitudElement = Integer.parseInt(this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar.element.longitud." + iIndex));
                                            if (longitudElement != valueCell.length()) {
                                                for (int iIndexLongitud = valueCell.length(); iIndexLongitud < longitudElement; iIndexLongitud++) {
                                                    String caracter = this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar.element.caracter." + iIndex);

                                                    valueCell = caracter + valueCell;
                                                }
                                            }
                                        }
                                    }

                                    CellCeilingBudgetDto myObj = new CellCeilingBudgetDto();
                                    myObj.setDataElement(valueCell);
                                    myObj.setRegisterId(myRow.getRowNum());

                                    objRowCeiling.add(myObj);
                                }
                                objList.add(objRowCeiling);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objList;
    }

    /**
     * Metodo que se encarga de crear el objeto que contendra la lista de todos
     * los datos cargados en los archivos para importacion de techos
     * presupuestales.
     *
     * @param files
     * @return
     */
    public List<List<CellCeilingBudgetDto>> loadDataFiles(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files) {
        List<List<CellCeilingBudgetDto>> objList = new ArrayList<List<CellCeilingBudgetDto>>();

        int iCountCell = 0;
        int iCountRow = 0;
        int iCell = 0;
        List<CellCeilingBudgetDto> objCellCeiling = new ArrayList<CellCeilingBudgetDto>();

        for (ImportFileCeillingBudgetDto importFile : files) {
            try {
                /**
                 * Creating Input Stream*
                 */
                //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
                FileInputStream myInput = new FileInputStream(importFile.getPathFile());

                /**
                 * Create a POIFSFileSystem object*
                 */
                POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                /**
                 * Create a workbook using the File System*
                 */
                HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

                for (int iSheet = 0; iSheet < myWorkBook.getNumberOfSheets(); iSheet++) {
                    /**
                     * Get the first sheet from workbook*
                     */
                    HSSFSheet mySheet = myWorkBook.getSheetAt(iSheet);

                    if (mySheet.getLastRowNum() > 0) {

                        Iterator rowIter = mySheet.rowIterator();
                        while (rowIter.hasNext()) {
                            HSSFRow myRow = (HSSFRow) rowIter.next();
                            if (myRow.getRowNum() > 0) {
                                iCountRow++;
                                iCountCell = myRow.getLastCellNum();
                                Iterator cellIter = myRow.cellIterator();
                                iCell = 0;
                                while (cellIter.hasNext()) {
                                    HSSFCell myCell = (HSSFCell) cellIter.next();

                                    CellCeilingBudgetDto myObj = new CellCeilingBudgetDto();

                                    myObj.setRegisterId(myRow.getRowNum());

                                    if (iCell < (iCountCell - 1)) {

                                        String valueCell = myCell.toString().replaceAll(",", "");
                                        Long typeElement = objLayout.get(iCell).getBudgetKeyId();

                                        int iCountElement = Integer.parseInt(this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar"));
                                        for (int iIndex = 0; iIndex < iCountElement; iIndex++) {
                                            Long element = Long.parseLong(this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar.element." + iIndex));
                                            if (element.longValue() == typeElement.longValue()) {
                                                valueCell = String.valueOf(Double.valueOf(valueCell).longValue());
                                                int longitudElement = Integer.parseInt(this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar.element.longitud." + iIndex));
                                                if (longitudElement != valueCell.length()) {
                                                    for (int iIndexLongitud = valueCell.length(); iIndexLongitud < longitudElement; iIndexLongitud++) {
                                                        String caracter = this.getMessage("ppp.techo.validationimportceilingbudget.countexception.completar.element.caracter." + iIndex);

                                                        valueCell = caracter + valueCell;
                                                    }
                                                }
                                            }
                                        }
                                        myObj.setElementType(typeElement);
                                        myObj.setDataElement(valueCell);
                                        myObj.setValidatorName(ValidationNameEnum.getValueOfBudgetKeyName(objLayout.get(iCell).getBudgetKeyName()));
                                    } else {
                                        myObj.setDataElement(myCell.toString().replaceAll(",", ""));
                                        myObj.setValidatorName(ValidationNameEnum.VALIDATION_UNIQUE_KEY);
                                    }

                                    objCellCeiling.add(myObj);
                                    iCell++;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            for (int iIndexCell = 0; iIndexCell < iCountCell; iIndexCell++) {
                List<CellCeilingBudgetDto> myList = new ArrayList<CellCeilingBudgetDto>();
                for (int iIndex = iIndexCell; iIndex < (iCountRow * iCountCell); iIndex += iCountCell) {
                    CellCeilingBudgetDto myObj = objCellCeiling.get(iIndex);
                    if (myObj.getDataElement() != null && myObj.getDataElement().length() > 0) {
                        myList.add(myObj);
                    }
                }
                objList.add(myList);
            }

        }
        return objList;
    }

    @Override
    public boolean saveCeillingBudgetItem(ValidateDeleteCeilingBudgetDto deleteCeiling, List<CeillingBudgetEntity> dataFile) {
        return itsImportCeilingBudgetDao.saveCeillingBudgetItem(deleteCeiling, dataFile);
    }

    @Override
    public List<CeillingBudgetEntity> executeImportCeilling(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files) {
        List<CeillingBudgetEntity> resulEntity = null;
        List<CellCeilingBudgetDto> resultDtoWithError = null;
        List<List<CellCeilingBudgetDto>> data = loadDataFiles(objLayout, files);
        ValidateCompImport composite = (ValidateCompImport) SIIFContextBase.getAppContext().getBean("composite");
        ValidationNameEnum ref = null;
        for (BudgetKeyItemEntity bidgetkey : objLayout) {
            ref = ValidationNameEnum.getValueOfBudgetKeyName(bidgetkey.getBudgetKeyName());

            composite.addValidator((ValidateCompImport) SIIFContextBase.getAppContext().getBean(ref.getBeanName()));

        }

        ref = ValidationNameEnum.getValueOfBudgetKeyName("validateData");
        composite.addValidator((ValidateCompImport) SIIFContextBase.getAppContext().getBean(ref.getBeanName()));


        resultDtoWithError = composite.executeValidation(data);
        resulEntity = this.convertDtoToEntity(composite.getEntitiesValid(), resultDtoWithError);

        return resulEntity;
    }

    private List<CeillingBudgetEntity> convertDtoToEntity(List<CellCeilingBudgetDto> lstDtoWithEntity, List<CellCeilingBudgetDto> resultDtoWithError) {
        List<CeillingBudgetEntity> resulEntity = new ArrayList<CeillingBudgetEntity>();
        boolean existElementError = false;
        for (CellCeilingBudgetDto objDto : lstDtoWithEntity) {
            for (CellCeilingBudgetDto objDtoError : resultDtoWithError) {
                if (objDto.getRegisterId() == objDtoError.getRegisterId()) {
                    existElementError = true;
                    break;
                }
            }
            if (!existElementError) {
                resulEntity.add(objDto.getObjentity());
            }
            existElementError = false;
        }

        return resulEntity;
    }

    @Override
    public List<CellCeilingBudgetDto> executeValidateFile(List<BudgetKeyItemEntity> objLayout, List<ImportFileCeillingBudgetDto> files) {
        List<CellCeilingBudgetDto> resulDto;
        List<List<CellCeilingBudgetDto>> data = loadDataFiles(objLayout, files);
        ValidateCompImport composite = (ValidateCompImport) SIIFContextBase.getAppContext().getBean("composite");
        ValidationNameEnum ref = null;
        for (BudgetKeyItemEntity bidgetkey : objLayout) {
            ref = ValidationNameEnum.getValueOfBudgetKeyName(bidgetkey.getBudgetKeyName());
            
            composite.addValidator((ValidateCompImport) SIIFContextBase.getAppContext().getBean(ref.getBeanName()));

        }

        ref = ValidationNameEnum.getValueOfBudgetKeyName("validateData");
        composite.addValidator((ValidateCompImport) SIIFContextBase.getAppContext().getBean(ref.getBeanName()));


        resulDto = composite.executeValidation(data);
        return resulDto;
    }
}
