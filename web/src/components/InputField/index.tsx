import './styles.scss';
import { InputType } from "../../types/InputType";

export default function InputField({
    inputId,
    placeholder,
    label,
    icon,
    type
}: InputType) {
    return (
        <div className="div-field">
            <label htmlFor={`${inputId}`}>
                {label}
            </label>

            <div className="div-input">
                {icon}
                <input
                    type={`${type}`}
                    id={`${inputId}`}
                    placeholder={`${placeholder}`}
                />
            </div>
        </div>
    );
}