Select AB.*,CD.basica from 
(Select a.*,SUBSTRING(a.clavecarga FROM 6 FOR 4) as objgasto 
from siifppptechopptal a where a.clavecarga like '0300%') as AB
join
(Select b.clave,a.basica from siifpppencdependenciaobjdestino a
join siifpppobjetogasto b on a.idobjetogasto=b.idobjetogasto
where a.iddependencia = 130) as CD on AB.objgasto=CD.clave;

Select * from siifabsdependencia where clave = '03 00';

Select * from siifabsdependencia where idpadre = 130
order by clave;