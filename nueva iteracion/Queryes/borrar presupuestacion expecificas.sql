begin work;

Select * from siifpppdetcveanteproy;
Select * from siifpppcvepptaldesglose;
Select * from siifpppdetallecvepptal;
Select * from siifpppdetcvepreficha;
Select * from siifpppcvemensualpptal;
Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100');

delete siifpppdetcveanteproy
where idcvepresupuestal in
(Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100'));
delete siifpppcvepptaldesglose
where idcvepresupuestal in
(Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100'));
delete siifpppdetallecvepptal
where idcvepresupuestal in
(Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100'));
delete siifpppdetcvepreficha
where idcvepresupuestal in
(Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100'));
delete siifpppcvemensualpptal
where idcvepresupuestal in
(Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100'));
delete siifpppcvepresupuestal
where idcvepresupuestal in
(Select idcvepresupuestal from siifpppcvepresupuestal 
where clave in ('08 005.00300182363100','08 005.00400187371100','08 005.00400187372100',
'04 001700600071441900','04 001700600071341100'));
rollback work;
commit work;