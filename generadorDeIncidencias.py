import json
import urllib
import urllib2
import random
import string
from datetime import datetime, timedelta

url = "http://localhost:8090/incidence-creator"
"""
Agent List Input
"""
agentList = []


"""
Incidence's field randomizer
"""
incNameRandomizer = lambda n: 'inc_' + ''.join(random.SystemRandom().choice(string.ascii_lowercase + string.digits+ string.ascii_uppercase) for _ in range(n))

def incDescriptionRandomizer():
    incDescriptions = ['Se han producido movimientos sismicos mas fuertes de lo habitual',
                       'Se ha producido un incendio',
                       'Se ha producido una inundacion',
                       'Nueva lectura de la temperatura',
                       'Nueva lectura de humedad']
    return incDescriptions[random.randint(0, len(incDescriptions)-1)]

def incLocalizationRandomizer():
    a, b = random.randint(-9999, 10000), random.randint(-9999, 10000)
    return '%d.%d' % (a, b)

def incExpirationDateRandomizer():
    return ""


def incStateRandomizer():
    return ""

incTagsRandomizer = lambda x: x
incMultimediaRandomizer = lambda x: x
incPropertiesRandomizer = lambda x: x


def generateRandomIncidence():
    return ""

"""
Send randomized incidences to an endpoint
"""
def sendIncidence(incidence):
    req = urllib2.Request(url, json.dumps(values), headers={'Content-type': 'application/json', 'Accept': 'application/json'})
    response = urllib2.urlopen(req)
    return response.read()

sendIncidences = lambda incidences: [sendIncidence(inc) for inc in incidences]



if '__main__':
    print "No ta acabao parse"
