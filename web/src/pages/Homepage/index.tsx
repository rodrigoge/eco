import HeaderComponent from '../../components/HeaderComponent';
import './styles.scss';
import heroImg from '../../assets/hero-image.svg';
import ButtonComponent from '../../components/ButtonComponent';

export default function Homepage() {
    return (
        <div className="home-container">
            <HeaderComponent />
            <div className="home-outbox">
                <div className="home-box">
                    <div className="hero-section">
                        <div className="hero-text">
                            <span>
                                Descarte os produtos e materiais eletrônicos de maneira <span className='contrast-word'>consciente</span>.
                            </span>

                            <ButtonComponent
                                className='btn btn-green'
                                size='btn-lg'
                                text='Descarte o seu produto'
                            />
                        </div>

                        <div className="hero-section-image">
                            <img src={heroImg} alt="Eco hero image" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}