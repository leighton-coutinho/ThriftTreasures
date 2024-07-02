import { useState } from 'react'
import {useNavigate} from 'react-router-dom'
import { useAuth } from '../common/AuthContext'
import "./LoginComponent.css"
import axios from 'axios';

function LoginComponent2() {

    const [username, setUsername] = useState('')

    const [password, setPassword] = useState('')

    const [showErrorMessage, setShowErrorMessage] = useState(false)

    const navigate = useNavigate()

    const authContext = useAuth()

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    function handleTestConnection() {
        axios.get('http://localhost:8081/test-connection')
            .then((res) => {
                console.log(res.data); // Log the response data to check the connection
            })
            .catch((error) => {
                console.error("There was an error!", error);
            });
    }

    async function handleSubmit() {
        if(await authContext.login2(username, password)){
            navigate(`/service`)
            //navigate(`/welcome/${username}`)
        } else {
            setShowErrorMessage(true)
        }
    }

    return (
        <div className="Login">
            <h1>Time to Login!</h1>
            {showErrorMessage && <div className="errorMessage">Authentication Failed. 
                                                            Please check your credentials.</div>}
            <div className="LoginForm">
                <div>
                    <label>User Name:</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>login</button>
                
                </div>
            </div>
        </div>
    ) // can add another button which submits for customers and keep the first one as for stores, just create a different handleSubmit function for each
}

export default LoginComponent2