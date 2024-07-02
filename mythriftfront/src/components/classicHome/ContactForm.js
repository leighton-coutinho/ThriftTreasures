import "./ContactFormStyles.css";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect, useRef } from 'react';
import emailjs from '@emailjs/browser';


function ContactForm (props) {
    let mymessage = "Send a message to us!"
    let desc = ""
    if (props.isSignUp == "true") {
        mymessage = "We are currently in Beta development!"
        desc = "Send us your information if you would like an account, either as a store or a user."
    }

    const [wasSent, setWasSent] = useState('');
    const form = useRef();
    const sendEmail = (e) => {
        e.preventDefault();
        if (wasSent == 'Your Message Was Sent') {
            return;
        }
        emailjs.sendForm('service_o2as0f2', 'template_6x2e4hj', form.current, 'ryLmZlzXjEBlKxDEA')
        .then((result) => {
            console.log(result.text);
        }, (error) => {
            console.log(error.text);
        });
        //set state here
        setWasSent( 'Your Message Was Sent');
        document.getElementById("form1").reset();
    }

    return (
        <div className="form-container">
            <h1> {mymessage} </h1>
            <h5> {desc} </h5>
            <form id="form1" ref={form} onSubmit={sendEmail}>
                <input name="user_name" placeholder="Name"/>
                <input name="user_email" placeholder="Email"/>
                <input placeholder="Subject"/>
                <textarea name="message" placeholder="Message" rows="4"></textarea>
                <h6>{wasSent}</h6>
                <button type="submit" form="form1" value="Submit">Send Message</button>
            </form>
        </div>
    );
}

export default ContactForm;