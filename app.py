from flask import Flask, request, redirect
import sqlite3
from datetime import datetime

app = Flask(__name__)

# Initialize DB
def init_db():
    conn = sqlite3.connect('phishing.db')
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS clicks 
                 (id INTEGER PRIMARY KEY AUTOINCREMENT, 
                  email TEXT, 
                  template TEXT, 
                  timestamp TEXT, 
                  ip TEXT)''')
    conn.commit()
    conn.close()

@app.route('/')
def home():
    return "Phishing Simulator is running!"

# Tracking link
@app.route('/track/<email>/<template>')
def track(email, template):
    conn = sqlite3.connect('phishing.db')
    c = conn.cursor()
    c.execute("INSERT INTO clicks (email, template, timestamp, ip) VALUES (?, ?, ?, ?)",
              (email, template, datetime.now().strftime("%Y-%m-%d %H:%M:%S"), request.remote_addr))
    conn.commit()
    conn.close()
    return redirect("/awareness")

@app.route('/awareness')
def awareness():
    return """
    <h1>Gotcha!</h1>
    <p>This was a simulated phishing attempt.</p>
    <p>Signs you should have noticed: Suspicious link, urgent tone, unknown sender.</p>
    """

if __name__ == "__main__":
    init_db()
    app.run(debug=True)
