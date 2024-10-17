import React, { forwardRef, useImperativeHandle, useState } from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  IconButton,
  TextField,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import PropTypes from "prop-types";

const ModalWindowWithForm = forwardRef((props, ref) => {
  const [open, setOpen] = useState(false);
  const [title, setTitle] = useState(props.windowTitle);
  const [formData, setFormData] = useState(props.formData || {});
  const [formFields, setFormFields] = useState(props.formFields || []); // Добавлено
  const [closeText, setCloseText] = useState(props.closeButtonText);
  const [submitText, setSubmitText] = useState(props.submitButtonText);
  const [handleSubmit, setHandleSubmit] = useState(() => () => {});

  useImperativeHandle(ref, () => ({
    openModal(
      newTitle,
      newFormData,
      newFormFields,
      newCloseButtonText,
      newSubmitButtonText,
      onSubmit
    ) {
      setTitle(newTitle);
      setFormData(newFormData || {});
      setFormFields(newFormFields || []); // Убедитесь, что formFields установлены
      setCloseText(newCloseButtonText);
      setSubmitText(newSubmitButtonText);
      setHandleSubmit(() => onSubmit);
      setOpen(true);
    },
  }));

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmitClick = async (event) => {
    event.preventDefault();
    const success = await handleSubmit(formData);
    if (success) handleClose();
  };

  const isRequired = (fieldName) => {
    const field = formFields.find((f) => f.name === fieldName);
    return field ? field.required : true;
  };

  return (
    <Dialog onClose={handleClose} open={open}>
      <DialogTitle>
        {title}
        <IconButton
          aria-label="close"
          onClick={handleClose}
          sx={{ position: "absolute", right: 8, top: 8 }}
        >
          <CloseIcon />
        </IconButton>
      </DialogTitle>
      <DialogContent>
        {Object.entries(formData).map(([key, value]) => (
          <TextField
            key={key}
            name={key}
            label={key.charAt(0).toUpperCase() + key.slice(1)}
            required={isRequired(key)}
            fullWidth
            value={value || ""}
            onChange={handleChange}
            variant="outlined"
            type={typeof value === "number" ? "number" : "text"}
            sx={{
              marginBottom: "10px",
              marginTop: "10px",
            }}
          />
        ))}
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose}>{closeText}</Button>
        <Button onClick={handleSubmitClick} variant="contained">
          {submitText}
        </Button>
      </DialogActions>
    </Dialog>
  );
});

ModalWindowWithForm.propTypes = {
  windowTitle: PropTypes.string.isRequired,
  formData: PropTypes.object,
  formFields: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      required: PropTypes.bool,
    })
  ).isRequired,
  closeButtonText: PropTypes.string.isRequired,
  submitButtonText: PropTypes.string.isRequired,
};

export default ModalWindowWithForm;
