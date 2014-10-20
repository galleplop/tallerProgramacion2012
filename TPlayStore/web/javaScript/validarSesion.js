/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function cambiarTipo(id){
                var fila = document.getElementById(id);
                fila.setAttribute('value', '');
                fila.setAttribute('type', 'password');
            }
            
            function oculto(id){
                    var fila = document.getElementsByName(id);
                    fila.setAttribute('type', 'hidden');
                    document.getElementById(id).setAttribute('type', 'hidden');
                    document.getElementById(id).style.visibility="hidden";
                    
            }



            function ocultar(id){
                var fila = document.getElementsByName(id);
                fila.setAttribute('type', 'hidden');
                document.getElementById(id).setAttribute('type', 'hidden');
                document.getElementById(id).style.visibility="hidden";
                
            }

            function mostrar(id){
                var elemento = document.getElementById(id);
                elemento.setAttribute('type', 'text');
            }

            function validarIS(){
                if (document.getElementById('nickMail') != null && ((document.getElementById('nickMail').value == '')||(document.getElementById('nickMail').value == 'nick o mail...'))) {
                   alert('Falta nick o mail');
                   return false;
               }else{
                   if (document.getElementById('pass') != null && ((document.getElementById('pass').value == '')||(document.getElementById('pass').value == 'contraseña...'))) {
                       alert('Falta contraseña');
                       return false;
                   }else{
                        
                        
                        return true;
                   }
               }
            }
            
            