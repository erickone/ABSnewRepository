begin work;

Select * from siifpppdetcveanteproy;
Select * from siifpppcvepptaldesglose;
Select * from siifpppdetallecvepptal;
Select * from siifpppdetcvepreficha;
Select * from siifpppcvemensualpptal;
Select * from siifpppcvepresupuestal;

delete siifpppdetcveanteproy;
delete siifpppcvepptaldesglose;
delete siifpppdetallecvepptal;
delete siifpppdetcvepreficha;
delete siifpppcvemensualpptal;
delete siifpppcvepresupuestal;

commit work;