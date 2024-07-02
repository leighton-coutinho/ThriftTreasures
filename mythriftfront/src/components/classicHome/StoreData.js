import "./StoreStyles.css";
import { useAuth } from '../common/AuthContext';
import { Link } from 'react-router-dom';
// add url to img to take you to store or product details
function StoreData(props) {
    const authContext = useAuth();

    return (
        <div className="r-card">
            <div className="r-image">
                <p>
                    <Link to={props.link}>
                        <img alt="image" src={props.image} />
                    </Link>
                </p>
            </div>
            <h4> {props.heading} </h4>
            <p> {props.text} </p>
        </div>
    );
}

export default StoreData;

