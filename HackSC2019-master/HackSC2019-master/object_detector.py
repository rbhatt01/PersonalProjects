from controller import capture
from flask import Flask, render_template

app = Flask(__name__)


@app.route('/', methods=["GET", "POST"])
def create():
    return render_template('test1.html')


@app.route("/start-webcam", methods=["GET", "POST"])
def activate():
    capture()


if __name__ == '__main__':
    app.run()
