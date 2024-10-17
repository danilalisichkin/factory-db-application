import React, { forwardRef, useImperativeHandle, useState } from "react";
import PropTypes from "prop-types";
import BootstrapDialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogActions from "@mui/material/DialogActions";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";
import Typography from "@mui/material/Typography";

const ModalWindow = forwardRef((props, ref) => {
  const [open, setOpen] = useState(false);
  const [title, setTitle] = useState(props.windowTitle);
  const [mainMsg, setMainMsg] = useState(props.mainMessage);
  const [additionalMsg, setAdditionalMsg] = useState(props.additionalMessage);
  const [closeText, setCloseText] = useState(props.closeButtonText);

  useImperativeHandle(ref, () => ({
    openModal(
      newTitle,
      newMainMessage,
      newAdditionalMessage,
      newCloseButtonText
    ) {
      setTitle(newTitle);
      setMainMsg(newMainMessage);
      setAdditionalMsg(newAdditionalMessage);
      setCloseText(newCloseButtonText);
      setOpen(true);
    },
  }));

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <BootstrapDialog
      onClose={handleClose}
      aria-labelledby="customized-dialog-title"
      open={open}
    >
      <DialogTitle sx={{ m: 0, p: 2 }} id="customized-dialog-title">
        {title}
        <IconButton
          aria-label="close"
          onClick={handleClose}
          sx={(theme) => ({
            position: "absolute",
            right: 8,
            top: 8,
            color: theme.palette.grey[500],
          })}
        >
          <CloseIcon />
        </IconButton>
      </DialogTitle>
      <DialogContent dividers>
        <Typography gutterBottom>{mainMsg}</Typography>
        <Typography gutterBottom>{additionalMsg}</Typography>
      </DialogContent>
      <DialogActions
        sx={{
          alignItems: "center",
        }}
      >
        <Button autoFocus onClick={handleClose}>
          {closeText}
        </Button>
      </DialogActions>
    </BootstrapDialog>
  );
});

ModalWindow.propTypes = {
  windowTitle: PropTypes.string.isRequired,
  mainMessage: PropTypes.string.isRequired,
  additionalMessage: PropTypes.string.isRequired,
  closeButtonText: PropTypes.string.isRequired,
};

export default ModalWindow;
