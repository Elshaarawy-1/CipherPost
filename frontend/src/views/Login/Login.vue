<template>
    <div class="layout">
        <div class="left-section">
            <img src="../../assets/Logo.png" alt="logo" style="width: 400px; height: 300px;border-bottom: ">
            <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Illo nulla optio officiis odio, quam iure fugit doloribus culpa numquam suscipit aspernatur, sapiente veniam ducimus accusamus eligendi quasi, harum earum corrupti in tempore! Eos repellat officia fuga consequuntur sunt quo a eaque praesentium asperiores in earum repudiandae dicta, temporibus sapiente quos! Quas earum eveniet dignissimos dolorem facere qui, libero temporibus possimus quisquam! Excepturi debitis dolor suscipit iusto? Distinctio ab, saepe ipsa rem nulla repellat corrupti voluptate aliquam tenetur libero nihil alias, vel, voluptates quo cum est impedit repudiandae id? Nesciunt numquam fuga ducimus animi recusandae commodi eos facilis enim earum a?</p>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Beatae unde ducimus cum eos, nemo architecto saepe vitae dignissimos eveniet quisquam, non inventore commodi, doloremque perspiciatis placeat molestias expedita odit at sequi autem modi quos! Veniam veritatis dolorum perspiciatis. Ad, voluptate odit praesentium perspiciatis odio similique aut, enim molestias, vitae ducimus porro tenetur optio et veniam omnis aperiam cum iure? Impedit velit quod quasi modi laudantium architecto mollitia amet eos et deleniti, laborum eligendi aperiam eum numquam unde iusto? Consequuntur itaque fugiat ipsa debitis qui expedita, excepturi veniam a reiciendis reprehenderit, iste mollitia corrupti iure facilis assumenda ullam ratione quae et!</p>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Recusandae amet veritatis quasi cupiditate rerum iure, iste dolores error magnam laudantium libero harum temporibus sint quidem maiores asperiores doloribus animi facere repellat laboriosam. Non quia neque reprehenderit a veniam est officiis odio. Ab inventore cupiditate aperiam ullam debitis minus labore, unde doloribus fugit magnam maiores quidem facere praesentium aspernatur culpa architecto nesciunt! Inventore suscipit quidem, porro delectus ullam quia odio recusandae, impedit deleniti doloremque temporibus corrupti enim ea molestiae atque maiores vel quam dignissimos nostrum animi! Dolore harum dolorem ab magnam minima atque, aliquid itaque! Quaerat, quam. Vel, perferendis eveniet? Fuga.</p>
        </div>

        <div class="right-section">
            <LoginForm />
        </div>
    </div>
</template>

<script>
import LoginForm from './LoginForm.vue'
export default {
    components: {LoginForm},
}
</script>

<style>
    .layout{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .layout .left-section{
        flex: 1;
        padding: 0px 20px 20px 20px;
        margin: 0px 0px 10px 70px;
        border: 1px solid #023047;
        border-radius: 20px;
        background-color: #fffaf1;
        box-shadow: 0px 4px 8px 0px rgba(0,0,0,0.45);
    }
    .layout .left-section p{
        text-align: left;
        color: #023047;
        font-family: sans-serif;
        font-weight:lighter;

    }
    .layout .right-section{
        flex: 1;
        padding: 10px;
    }
</style>