import "./HeroStyles.css";

function Hero (props) {
    return (
        <>
            <div className={props.cName}>
                <img alt="HeroImg" src={props.heroImg}/>
            </div>

            <div className="hero-text">
                <h1>{props.title}</h1>
                <p>{props.text}</p>
                <a href={props.url} className={props.btnClass}>
                    {props.buttonText}
                </a>
                <a href={props.url2} className={props.btnClass}>
                    {props.buttonText2}
                </a>
            </div>
        </>

    );
}

export default Hero;