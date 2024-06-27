import "./FooterStyles.css";

function Footer () {
    return (
        <div className="footer">
            <div className="top">
                <div>
                    <h1>ThriftTreasures</h1>
                    <p> The platform for listing</p>
                </div>
                <div>
                    <a href="/">
                        <i className="fa-brands fa-facebook-square"></i>
                    </a>
                    <a href="/">
                        <i className="fa-brands fa-instagram-square"></i>
                    </a>
                    <a href="/">
                        <i className="fa-brands fa-behance-square"></i>
                    </a>
                    <a href="/">
                        <i className="fa-brands fa-twitter-square"></i>
                    </a>
                </div>
            </div>
        </div>
    );
}

export default Footer;