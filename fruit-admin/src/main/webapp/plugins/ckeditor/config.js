/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    // config.uiColor = '#AADC6E';
    config.toolbar = 'Full',
        config.width = 750,
        config.height = 200,
        config.filebrowserUploadUrl = '/admin/commons/upload/images?type=file',
        config.filebrowserImageUploadUrl = '/admin/commons/upload/images?type=image',
        config.filebrowserFlashUploadUrl = '/admin/commons/upload/images?type=flash',
        config.enterMode = CKEDITOR.ENTER_BR,
        config.shiftEnterMode = CKEDITOR.ENTER_BR,
        config.toolbar_Full = [
            ['Source', 'Cut', 'Copy', 'Undo', 'Redo', 'Paste', 'PasteFromWord', '-', 'SpellChecker'],
            ['Styles', 'Format', 'Font', 'FontSize', 'TextColor', 'BGColor', 'Checkbox', 'Radio'],
            '/',
            ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
            ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent'],
            ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
            ['Image', 'Table', 'HorizontalRule', 'SpecialChar', 'PageBreak']
        ];

};
/*
 config.toolbar_Full = [
 ['Source','-','Save','NewPage','Preview','-','Templates'],
 ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print','SpellChecker', 'Scayt'],
 ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
 ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select','Button', 'ImageButton', 'HiddenField'],
 '/',
 ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
 ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
 ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
 ['Link','Unlink','Anchor'],
 ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
 '/',
 ['Styles','Format','Font','FontSize'],
 ['TextColor','BGColor']
 ];*/
