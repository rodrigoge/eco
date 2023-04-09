import { useState } from 'react';
import './styles.scss';
import { InputType } from "../../types/InputType";
import { AiOutlineEye, AiOutlineEyeInvisible } from 'react-icons/ai';

export default function InputFieldComponent({
    inputId,
    placeholder,
    label,
    icon,
    type,
    isPassword,
    value,
    onChange,
    maxLength
}: InputType) {
    const [showPassword, setShowPassword] = useState(true);

    async function handleShowPassword() {
        var inputPassword: any = document.getElementById('password-id');
        if (inputPassword.type === 'password') {
            inputPassword.type = 'text'
            setShowPassword(false);
        } else {
            inputPassword.type = 'password'
            setShowPassword(true);
        }
    }

    return (
        <div className="div-field">
            <label htmlFor={`${inputId}`}>
                {label}
            </label>

            <div className="div-input">
                <span>{icon}</span>
                <input
                    type={`${type}`}
                    id={`${inputId}`}
                    placeholder={`${placeholder}`}
                    value={value}
                    onChange={onChange}
                    maxLength={maxLength}
                />

                {isPassword ?
                    <>
                        {showPassword ?
                            <span className='showEyeIcon' onClick={handleShowPassword}>
                                < AiOutlineEyeInvisible />
                            </span>
                            :
                            <span className='showEyeIcon' onClick={handleShowPassword}>
                                <AiOutlineEye />
                            </span>
                        }
                    </>
                    :
                    <></>
                }

            </div>
        </div >
    );
}