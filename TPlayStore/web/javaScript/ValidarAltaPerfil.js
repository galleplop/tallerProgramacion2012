function habilitarWeb(identificador_fila){
    var fila = document.getElementById(identificador_fila);
    document.getElementById("labelWeb").style.visibility="visible";
    fila.setAttribute('type', 'text');
}

function desHabilitarWeb(identificador_fila){
    var fila = document.getElementById(identificador_fila);
    document.getElementById("labelWeb").style.visibility="hidden";
    fila.setAttribute('type', 'hidden');
}

function validar(){
    if (document.getElementById('nic') != null && document.getElementById('nic').value == '') {
        alert('Falta nick');
        return false;
    }else{
        if (document.getElementById('mail') != null && document.getElementById('mail').value == '') {
            alert('Falta mail');
            return false;
        }else{
            if (document.getElementById('nombre') != null && document.getElementById('nombre').value == '') {
                alert('Falta nombre');
                return false;
            }else{
                if (document.getElementById('apellido') != null && document.getElementById('apellido').value == '') {
                    alert('Falta apellido');
                    return false;
                }else{
                    if (document.getElementById('contra') != null && document.getElementById('contra').value == '') {
                        alert('Falta contraseña');
                        return false;
                    }else{
                        if (document.getElementById('verifContra') != null && document.getElementById('verifContra').value == '') {
                            alert('Falta validacion de contraseña');
                            return false;
                        }else{
                            if (document.getElementById('verifContra').value != document.getElementById('contra').value) {
                                alert('Contraseñas no coinciden');
                                return false;
                            }else{
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }
}
var ajaxNick;
var ajaxMail;

function sendAjaxNick(){
    if( window.XMLHttpRequest ){
	ajaxNick = new XMLHttpRequest(); // No Internet Explorer
    } else {
	ajaxNick = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
    }
    var theNick = document.getElementById("nic").value;
    if (theNick.length>3){
        ajaxNick.onreadystatechange = responseAjaxNick;
        ajaxNick.open("GET", "/checkPerfil?nick=" + theNick);///validarNick?nick=" + theNick
        ajaxNick.send("");
    } else {
        document.getElementById("checkName").innerHTML = "";
    }
}

function responseAjaxNick(){

    if (ajaxNick.readyState == 4){//Manda 4 respuesta. :| . La ultima es la posta. :)
        // Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
        if( ajaxNick.status == 200 ){
            // Escribimos el resultado en la pagina HTML mediante DHTML
            document.getElementById("checkName").innerHTML = ajaxNick.responseText;
        } else {
            document.getElementById("checkName").innerHTML = "";
        }
    }
}

function sendAjaxMail(){
    
    if( window.XMLHttpRequest ){
	ajaxMail = new XMLHttpRequest(); // No Internet Explorer
    } else {
	ajaxMail = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
    }
    var theMail = document.getElementById("mail").value;
    if (theMail.length>3){
        ajaxMail.onreadystatechange = responseAjaxMail;
        ajaxMail.open("GET", "/checkPerfil?mail=" + theMail);///validarNick?nick=" + theNick
        ajaxMail.send("");
    } else {
        document.getElementById("checkMail").innerHTML = "";
    }
}

function responseAjaxMail(){

    if (ajaxMail.readyState == 4){//Manda 4 respuesta. :| . La ultima es la posta. :)
        // Comprobamos si la respuesta ha sido correcta (resultado HTTP 200)
        if( ajaxMail.status == 200 ){
            
            // Escribimos el resultado en la pagina HTML mediante DHTML
            document.getElementById("checkMail").innerHTML = ajaxMail.responseText;
        } else {
            document.getElementById("checkMail").innerHTML = "";
        }
    }
}

$(function() {
    $("#datepicker").datepicker({
        dateFormat: 'd/m/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "1890:2012"    
    });
});

