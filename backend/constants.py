"""
Application constants
"""
import os

# Firebase
os.environ['FIREBASE_API_KEY'] = 'AIzaSyCcwx6Kfm9g_cp79Ot_TlNp2Vk9BH7imJE'
os.environ['AUTH_DOMAIN'] = 'mcc-fall-2017-g12.firebaseapp.com'
os.environ['DATABASE_URL'] = 'https://mcc-fall-2017-g12.firebaseio.com'
os.environ['PROJECT_ID'] = 'mcc-fall-2017-g12'
os.environ['STORAGE_BUCKET'] = 'mcc-fall-2017-g12.appspot.com'

# Google Vision
os.environ['VISION_API_KEY'] = 'AIzaSyBVRD-TNvMAWMlbQd7i9Sb17cQztEVIafc'

#Interval for Inactive groups removal(Time in secs)
os.environ['HOUSEKEEPING_INTERVAL'] = '5'
