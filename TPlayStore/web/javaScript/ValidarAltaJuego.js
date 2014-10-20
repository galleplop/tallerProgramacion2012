/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var ajaxName;

function sendAjaxName(){
    if( window.XMLHttpRequest ){
	ajaxName = new XMLHttpRequest(); // No Internet Explorer
    } else {
	ajaxName = new ActiveXObject("Microsoft.XMLHTTP"); // Internet Explorer
    }
    var theNick = document.getElementById("nombreJuego").value;
    if (theNick.length == 0){
    	document.getElementById("JuegoSpan").innerHTML = "";
    } else {
    	ajaxName.onreadystatechange = responseAjaxName;
        ajaxName.open("GET", "/checkJuego?juego=" + theNick);
        ajaxName.send("");
    }
}

function responseAjaxName(){

    if (ajaxName.readyState == 4){
        
        if( ajaxName.status == 200 ){
            document.getElementById("JuegoSpan").innerHTML = ajaxName.responseText;
        } else {
            document.getElementById("JuegoSpan").innerHTML = "";
        }
    }
}
//seteo de checkout

function setCheckBox(idCheckBox){
    
    idCheckBox.value = (idCheckBox.value == "OFF")? "ON":"OFF";
}

//check campos

function checkCategoria() {
    var i = 0;
    var checkBox = document.getElementById("cat" + i);
    while (checkBox != null && checkBox.value != ""){
        if (checkBox.value == "ON"){
            return true;
        }
        i++;
        checkBox = document.getElementById("cat" + i);
    }
    return false;
}

function checkJar(){
    
    var jarPath = document.getElementById("fileJuego").value;
    if (jarPath == null && jarPath == ""){
        return false;
    } else {
        var parser = jarPath.split(".");
        return parser[parser.length -1] == "jar";
    }
}

function checkPrecio(){
    
    var precio = document.getElementById("precioJuego").value;
    return (precio != null && precio != "" && !isNaN(precio));
}

function checkNombre(){
    
    var statusJuego = document.getElementById("JuegoSpan").innerHTML;
    if (statusJuego == ""){
        document.getElementById("JuegoSpan").innerHTML = "<a style='color: red; font-size:16px;'> No Valido. </a>";
        return false;
    } else {
        return (statusJuego.search(" NO ") == -1);
    }
}

function checkVersion(){
    
    return document.getElementById("versionJuego").value != "";
}


function checkSumit(){
    var ok = true;
    if (!this.checkCategoria()){
        document.getElementById("catSpan").innerHTML = "<a style='color: red; font-size:16px;'> Seleccione una Categoria </a>";
        ok = false;
    } else {
        document.getElementById("catSpan").innerHTML = "";
    }
    
    if (!this.checkJar()){
        document.getElementById("jarSpan").innerHTML = "<a style='color: red; font-size:16px;'> Ingrese un .jar </a>";
        ok = false;
    } else {
        document.getElementById("jarSpan").innerHTML = "";
    }
    
    if (!this.checkPrecio()){
        document.getElementById("precioSpan").innerHTML = "<a style='color: red; font-size:16px;'> No Valido </a>";
        document.getElementById("precioJuego").value = "";
        ok = false;
    } else {
        document.getElementById("precioSpan").innerHTML = "";
    }
    
    if (!this.checkNombre()){
        ok = false;
    }
    if (!this.checkVersion()){
        document.getElementById("VersionSpan").innerHTML = "<a style='color: red; font-size:16px;'> No Valido. </a>";
        ok = false;
    } else {
        document.getElementById("VersionSpan").innerHTML = "";
    }
    return ok;
}