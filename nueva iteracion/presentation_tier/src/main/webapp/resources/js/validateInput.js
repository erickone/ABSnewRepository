/* 
 *  Copyright (C) 2012 Advance Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  validateInput
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advance Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advance
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */


//<![CDATA[
function soloNumeros(evt, input) {

    var charCode = (evt.which) ? evt.which : evt.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57) 
                && charCode != 13 && charCode != 8 && charCode != 44 )
        {
            return false;  
        }
        else
        {
            return true;
        }   
}
//]]>
//
//<![CDATA[
function format(input)
{
    var num = input.value.replace(/\,/g,'');
    if(!isNaN(num)){
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\,?)(\d{3})/g,'$1,');
        num = num.split('').reverse().join('').replace(/^[\,]/,'');
        input.value = num;
    }
    else{ 
        alert('Solo se permiten numeros');
        input.value = input.value.replace(/[^\d\,]*/g,'');
    }

}//]]>

function ceilingDesc(e){
item = e.item;
var one=item.text();
var key=one.split(" ");
var myText = document.getElementById('frmCatTechoPresupuestal:txtDescripcion');
var myNewText = "";
// alert(myText.value);
for(var i=0; i<key.length;i++){
    if(key[i]!="" && key[i]!="\n"){
        // alert(i+" : "+key[i]+"."+key[i].length);
        myNewText = myText.value+" "+key[i];
        // alert(myNewText);
        i=key.length+1;
    }
}
document.getElementById('frmCatTechoPresupuestal:txtDescripcion').value = myNewText;
//alert(myNewText);
myText = myNewText;
}