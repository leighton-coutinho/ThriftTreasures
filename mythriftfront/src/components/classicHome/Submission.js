import DestinationData from "./SubmissionData";
import "./SubmissionStyles.css";

function Submission() {
    return (
        <>
            <div className="destination">
                <h1>How it works?</h1>
                <p>Step by Step guide for thrifters and stores:</p>

                <DestinationData
                    className="first-des"
                    heading="For thrifters"
                    text="You can use this website in order to view your favourite thrift stores newest pieces.
                        These may be popular pieces that you can bid on, if you win then you will be able to pick up your new favourite piece in store."
                    img1="https://img.freepik.com/premium-photo/young-red-haired-man-browsing-clothes-racks-while-shopping-sustainably-thrift-shop_236854-45433.jpg"
                    img2="https://uservision.co.uk/uploads/store/mediaupload/379/image/l_limit-Asosvisualsearch.jpg"
                /> 
                <DestinationData
                    className="first-des-reverse"
                    heading="For Stores"
                    text="You can use this website in order to list pieces in your store that you belive your customers will find interesting.
                        These popular pieces can then be bid on or bought straight away after which customers will be driven to your store.
                        To sign up click the sign up button and send us a message regarding your current situation."
                    img1="https://thesavvycouple.com/wp-content/uploads/2024/05/Best-Place-to-Sell-Clothes-377x251.jpg"
                    img2="https://images.pexels.com/photos/12935042/pexels-photo-12935042.jpeg"
                /> 
            </div>
        </>
    );
}

export default Submission;