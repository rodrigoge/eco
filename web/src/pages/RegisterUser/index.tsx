import { useState } from 'react'
import './styles.scss';
import { HiOutlineMail } from 'react-icons/hi'
import { AiOutlineUser } from 'react-icons/ai'
import { BiLockAlt } from 'react-icons/bi'
import registerImg from '../../assets/register-image.svg'
import InputField from '../../components/InputFieldComponent';
import ButtonComponent from '../../components/ButtonComponent';

export default function RegisterUser() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    return (
        <div className='register-user-container'>
            <form>
                <div className='register-user-aside'>
                    <header>
                        <h2>Cadastre-se agora na <span>eco</span>.</h2>
                    </header>

                    <InputField
                        inputId='name-id'
                        label='Nome Completo'
                        placeholder='Preencha com o seu nome completo'
                        type='text'
                        icon={<AiOutlineUser />}
                        isPassword={false}
                        value={name}
                        onChange={e => setName(e.target.value)}
                        maxLength={255}
                    />

                    <InputField
                        inputId='email-id'
                        label='E-mail'
                        placeholder='Preencha com o seu e-mail'
                        type='text'
                        icon={<HiOutlineMail />}
                        isPassword={false}
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        maxLength={255}
                    />

                    <InputField
                        inputId='password-id'
                        label='Senha'
                        placeholder='Preencha com a sua senha'
                        type='password'
                        icon={<BiLockAlt />}
                        isPassword={true}
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        maxLength={8}
                    />

                    <ButtonComponent
                        className='btn btn-green'
                        size='btn-lg'
                        text='Cadastrar'
                    />
                </div>

                <aside>
                    <img src={registerImg} alt="Register user image" />
                </aside>
            </form>
        </div>
    );
}