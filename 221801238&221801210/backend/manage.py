from flask_migrate import MigrateCommand
from flask_script import Manager
from gevent import monkey
from App import create_app
from App.ext import db

monkey.patch_all()

app = create_app()

manager = Manager(app=app)

manager.add_command('db', MigrateCommand)

@manager.command
def runserver_gevent():
    from gevent import pywsgi
    server = pywsgi.WSGIServer(("0.0.0.0",8090),app)
    server.serve_forever()

if __name__ == '__main__':
    manager.run()