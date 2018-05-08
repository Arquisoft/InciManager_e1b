expirationTime = "2019-05-15 10:02:29.769579";
additionalInfo = "http://puntoverdeleon.com.mx/wp-content/uploads/2016/09/imagen-de-prueba-320x240.jpg";
properties = "p0:v0,p1:v1,p2:v2,p3:v3";

genAlphanumeric = () => Math.round((Math.pow(36, 5 + 1) - Math.random() * Math.pow(36, 5))).toString(36).slice(1);
genIncName = () => "inc_" + genAlphanumeric();
genOper = () => "oper_" + genAlphanumeric();
genCoord = () => (Math.random()*((90-(-90))+(-90))).toFixed(4);
genLoc = () => genCoord() + "," + genCoord();

function genDesc(){
  incDescriptions = ['Se han producido movimientos sismicos mas fuertes de lo habitual',
                     'Se ha producido un incendio',
                     'Se ha producido una inundacion',
                     'Nueva lectura de la temperatura',
                     'Nueva lectura de humedad'];

  return incDescriptions[Math.round(Math.random() * (incDescriptions.length-1))];
}

function genTags(){
  incTags = ["Lluvia", "Fuego", "Nieve", "Niebla", "Terremoto", "Inundacion"]
  return incTags[Math.round(Math.random() * (incTags.length-1))];
}


function fillForm(){
  document.getElementById("incName").value = genIncName();
  document.getElementById("incDesc").value = genDesc();
  document.getElementById("incLoc").value = genLoc();
  document.getElementById("incTags").value = genTags();
  document.getElementById("incAdditionalInfo").value = additionalInfo;
  document.getElementById("incProperties").value = properties;
  document.getElementById("incExpirationDate").value = expirationTime;
  document.getElementById("incOper").value = genOper();
  document.getElementById("incDesc").value = genDesc();
}
