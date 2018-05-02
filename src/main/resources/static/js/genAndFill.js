expirationTime = "2019-05-15 10:02:29.769579";
additionalInfo = "http://puntoverdeleon.com.mx/wp-content/uploads/2016/09/imagen-de-prueba-320x240.jpg";
properties = "p0:v0,p1:v1,p2:v2,p3:v3";

genAlphanumeric = () => Math.round((Math.pow(36, 5 + 1) - Math.random() * Math.pow(36, 5))).toString(36).slice(1);
genIncName = () => "inc_" + genAlphanumeric();
genOper = () => "oper_" + genAlphanumeric();
genCoord = () => (Math.random()*((100-(-90))+(-90))).toFixed(4);
genLoc = () => genCoord() + "," + genCoord();

function genDesc(){
  incDescriptions = ['Se han producido movimientos sismicos mas fuertes de lo habitual',
                     'Se ha producido un incendio',
                     'Se ha producido una inundacion',
                     'Nueva lectura de la temperatura',
                     'Nueva lectura de humedad'];

  return incDescriptions[Math.round(Math.random() * (incDescriptions.length-1))];
}
