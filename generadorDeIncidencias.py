import json
import urllib
import urllib2
import random
import string
from datetime import datetime, timedelta
import sys, time


url = "http://localhost:8090/incidence-creator"
"""
Agent List Input
"""
agentList = [
                {
                    "ident":"12345678P",
                    "password":"123456",
                    "kind":1
                },
                {
                    "ident":"12345678A",
                    "password":"123456",
                    "kind":1
                },
                {
                    "ident":"entidad1",
                    "password":"123456",
                    "kind":2
                },
                {
                    "ident":"entidad2",
                    "password":"123456",
                    "kind":2
                },
                {
                    "ident":"sensor1",
                    "password":"123456",
                    "kind":3
                },
                {
                    "ident":"sensor2",
                    "password":"123456",
                    "kind":3
                }
]



"""
Incidence's field randomizer
"""

getRandomAgent = lambda: agentList[random.randint(0, len(agentList)-1)]
incNameRandomizer = lambda n: 'inc_' + ''.join(random.SystemRandom().choice(string.ascii_lowercase + string.digits+ string.ascii_uppercase) for _ in range(n))

def incDescriptionRandomizer():
    incDescriptions = ['Se han producido movimientos sismicos mas fuertes de lo habitual',
                       'Se ha producido un incendio',
                       'Se ha producido una inundacion',
                       'Nueva lectura de la temperatura',
                       'Nueva lectura de humedad']
    return incDescriptions[random.randint(0, len(incDescriptions)-1)]

def incLocalizationRandomizer():
    a, b = random.randint(-99, 100, random.randint(-90 100))
    return '%d,%d' % (a, b)

incOperarioRandomizer = lambda n: 'oper_' + ''.join(random.SystemRandom().choice(string.ascii_lowercase + string.digits+ string.ascii_uppercase) for _ in range(n))

def incExpirationDateRandomizer():
    return datetime.now() + timedelta(seconds= int((datetime(2019,3,25,0,16,20,220650) - datetime.now()).total_seconds() * random.random()))

def incStateRandomizer():
    incStates = ["Abierta", "Cerrada", "En proceso", "Anulada"]
    return incStates[random.randint(0, len(incStates)-1)]

def incTagsRandomizer(n):
    incTags = ["Lluvia", "Fuego", "Nieve", "Niebla", "Terremoto", "Inundacion"]
    a = lambda: incTags[random.randint(0, len(incTags)-1)]
    return "[" + ",".join(['"'+a()+'"' for x in range(n)]) + "]"

def incMultimediaRandomizer():
    return "http://puntoverdeleon.com.mx/wp-content/uploads/2016/09/imagen-de-prueba-320x240.jpg"


def incPropertiesRandomizer(n):
    return ','.join(['"p%d":"v%d"' % (x,x) for x in range(n)])

def generateRandomIncidence():
    jsonResult = '''
{
  "ident":"%s",
  "password":"%s",
  "kind":%d,
  "name":"%s",
  "description":"%s",
  "location":"%s",
  "tags":%s,
  "additionalInformation":"%s",
  "properties":{
    %s
  },
  "state":"%s",
  "notification":"si",
  "expireAt":"%s",
  "assignedTo":"%s"
}
    '''

    agent = getRandomAgent()
    return (jsonResult % (agent["ident"], agent["password"], agent["kind"],
                        incNameRandomizer(4), incDescriptionRandomizer(), incLocalizationRandomizer(),
                        incTagsRandomizer(4), incMultimediaRandomizer(), incPropertiesRandomizer(4),
                        incStateRandomizer(), incExpirationDateRandomizer(), incOperarioRandomizer(4))).strip()


"""
Send randomized incidences to an endpoint
"""
def sendIncidence(incidence):
    incidence = json.loads(incidence)
    req = urllib2.Request(url, json.dumps(incidence), headers={'Content-type': 'application/json', 'Accept': 'application/json'})
    response = urllib2.urlopen(req)
    return response.read()

sendIncidences = lambda n: [sendIncidence(generateRandomIncidence()) for _ in range(int(n))]


if '__main__':
   print sendIncidences(sys.argv[1])
