import './styles.scss';
import logo from '../../assets/logo.svg';
import ButtonComponent from '../../components/ButtonComponent';

export default function HeaderComponent() {
    return (
        <header>
            <img src={logo} alt="Logotype echo" />

            <div className='box'>
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
        </header>
    );
}