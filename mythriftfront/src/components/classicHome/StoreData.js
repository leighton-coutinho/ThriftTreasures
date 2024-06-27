import "./StoreStyles.css";
// add url to img to take you to store or product details
function StoreData(props) {
    return (
        <div className="r-card">
            <div className="r-image">
            <p><a href={props.link}>
            <img alt="image" src={props.image} />
            </a></p>
            </div>
            <h4> {props.heading} </h4>
            <p> {props.text} </p>
        </div>
    );
}

export default StoreData;
