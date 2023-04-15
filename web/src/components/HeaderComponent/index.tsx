import './styles.scss';
import logo from '../../assets/logo.svg';
import ButtonComponent from '../../components/ButtonComponent';
import { useNavigate } from 'react-router-dom';
import { HeaderType } from '../../types/HeaderType';

export default function HeaderComponent({ isHome }: HeaderType) {
    const navigate = useNavigate()

    async function sendToRegisterUser() {
        navigate('/user/register')
    }

    async function sendToPoints() {
        navigate('/points')
    }

    return (
        <header>
            <img src={logo} alt="Logotype echo" />

            {isHome ?
                <>
                    <div className='box'>
                        <ButtonComponent
                            className='btn btn-blue'
                            size='btn-sm'
                            text='Cadastrar'
                            onClick={sendToRegisterUser}
                        />
                        <ButtonComponent
                            className='btn btn-green'
                            size='btn-sm'
                            text='Descartar'
                            onClick={sendToPoints}
                        />
                    </div>
                </>
                :
                <>
                </>}
        </header>
    );
}