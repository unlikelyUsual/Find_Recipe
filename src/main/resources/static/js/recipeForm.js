$(document).ready(()=>{

    $('#addIngredient').on('click',(ev)=>{
        ev.preventDefault();
        const template = $('#ingredientTemplate').html();
        const container = $('#ingredientContainer');
        const range = $('#ingredientSize');
        let size = range.val() !== '' ? parseInt(range.val()) : 0 ;
        let string =template
            .replace(/{{amountName}}/g,"ingredients["+ size +"].amount")
            .replace(/{{uomName}}/g,"ingredients["+size +"].unitOfMeasure.description")
            .replace(/{{uomId}}/g,"ingredients["+size +"].unitOfMeasure.id")
            .replace(/{{descriptionName}}/g,"ingredients["+size +"].description");
        container.append(string);
        range.val(size++);

    });

    $('#uploadImage').on('click',(ev)=>{
       ev.preventDefault();
       $('#image').trigger('click');
    });

    const uploadImage = async (image) =>{
        const formData  = new FormData();
        formData.append("file",image);
        formData.append("recipe",$('#id').val());
        await fetch('/recipe/uploadImage',{
            method : 'POST',
            headers : {
                //'Content-Type': 'multipart/form-data'
            },
            body : formData
        });
    };

    $('#image').change(function (ev) {
        if(this.files.length > 0){
            uploadImage(this.files[0])
                .then(res=>{
                    window.alert("Uploaded");
                   window.location.reload()
                })
                .catch(err=>console.log(err));
        }
    });
});

function setId(select) {
    const $this = $(select);
    const id = $this.closest(".form-group").find('.uomId');
    id.val($this.find('option:selected').attr('id'));
}