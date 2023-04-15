import { Link } from 'react-router-dom';
import ButtonComponent from '../../components/ButtonComponent';
import HeaderComponent from '../../components/HeaderComponent';
import './styles.scss';
import { AiOutlineArrowLeft } from 'react-icons/ai';

export default function ListPoints() {
    return (
        <div className="points-container">
            <HeaderComponent />

            <div className="ecopoints">
                <header>
                    <h2>Lista de Ecopontos disponíveis</h2>

                    <Link to={"/"} className='back-page'>
                        <AiOutlineArrowLeft />
                        <span>Voltar</span>
                    </Link>
                </header>

                <div className="cards-in-row">
                    <div className="cards-container">
                        <div className="card">
                            <h3>Ecoponto São Paulo</h3>

                            <div className="ecopoint-content">
                                <span>Centro de Reciclagem</span>
                                <span>São Paulo - SP</span>
                                <span>07h às 22h</span>
                            </div>

                            <div className="ecopoint-buttons">
                                <ButtonComponent
                                    className='btn btn-blue'
                                    size='btn-sm'
                                    text='Cadastrar'
                                />
                                <ButtonComponent
                                    className='btn btn-green'
                                    size='btn-sm'
                                    text='Descartar'
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}